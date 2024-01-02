package com.loanBuddyApp.userService.service;

import com.loanBuddyApp.userService.dto.LoanRequest;
import com.loanBuddyApp.userService.dto.TransactionRequest;
import com.loanBuddyApp.userService.model.Loan;
import com.loanBuddyApp.userService.model.Transaction;
import com.loanBuddyApp.userService.model.User;
import com.loanBuddyApp.userService.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;
import java.util.Date;
import java.util.UUID;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService{

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final MongoTemplate mongoTemplate;
    @Override
    public Transaction transferMoney(TransactionRequest transactionRequest, String authorizationHeader) throws Exception {
        String tokenDetails = authorizationHeader.substring(7);
        String fromUserName = jwtService.extractUserName(tokenDetails);
        String toUserName = transactionRequest.getToUserName();
        double transferAmount = transactionRequest.getAmount();
        if(userRepo.existsUserByUserName(toUserName) && userRepo.existsUserByUserName(fromUserName) && checkIfUserHasSufficientBalance(fromUserName, transferAmount) && checkIfBothCustomersAreUsers(fromUserName, toUserName)){
            Transaction transaction=Transaction.builder()
                    .transactionID(generateUniqueID())
                    .toUserName(transactionRequest.getToUserName())
                    .amount(transactionRequest.getAmount())
                    .timestamp(new Date())
                    .description(transactionRequest.getDescription())
                    .build();
            Query query = new Query(Criteria.where("userName").is(fromUserName));
            Update update = new Update().push("userTransactions", transaction);
            mongoTemplate.updateFirst(query, update, User.class);
            update = new Update().inc("userBankBalance", -transferAmount);
            mongoTemplate.updateFirst(query, update, User.class);
            query = new Query(Criteria.where("userName").is(toUserName));
            update = new Update().inc("userBankBalance", transferAmount);
            mongoTemplate.updateFirst(query, update, User.class);
            return transaction;
        }
        else {
            throw new Exception("Transaction Cannot be Completed !");
        }

    }

    @Override
    public Loan loanRequest(String userName, LoanRequest loanRequest) {
        String loanID = userName.substring(0,4).concat(generateUniqueID());
        Loan loan = Loan.builder()
                .loanID(loanID)
                .loanAmount(loanRequest.getLoanAmount())
                .loanDescription(loanRequest.getLoanDescription())
                .creditScore(loanRequest.getCreditScore())
                .loanCollateral(loanRequest.isLoanCollateral())
                .ssnNumber(loanRequest.getSsnNumber())
                .annualSalary(loanRequest.getAnnualSalary())
                .propertiesPossessed(loanRequest.getPropertiesPossessed())
                .netWorth(loanRequest.getNetWorth())
                .netWorthDocumentation(loanRequest.getNetWorthDocumentation())
                .build();
        Query query = new Query(Criteria.where("userName").is(userName));
        Update update = new Update().push("userLoans", loan);
        mongoTemplate.updateFirst(query, update, User.class);

        return loan;
    }

    private boolean checkIfBothCustomersAreUsers(String fromUserName, String toUserName) {
        return userRepo.findByUserName(fromUserName).get().getUserRole().toString().equals("USER") && userRepo.findByUserName(toUserName).get().getUserRole().toString().equals("USER");
    }

    private boolean checkIfUserHasSufficientBalance(String fromUserName, double transferAmount) {
        Optional<User> user =userRepo.findByUserName(fromUserName);
        double currentBankBalance = user.get().getUserBankBalance();
        return currentBankBalance >= transferAmount;

    }

    private String generateUniqueID(){
        return UUID.randomUUID().toString();
    }
}

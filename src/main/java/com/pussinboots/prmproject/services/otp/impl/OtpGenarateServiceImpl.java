package com.pussinboots.prmproject.services.otp.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pussinboots.prmproject.data.entities.Customer;
import com.pussinboots.prmproject.data.entities.Staff;
import com.pussinboots.prmproject.data.repositories.CustomerRepository;
import com.pussinboots.prmproject.data.repositories.StaffRepository;
import com.pussinboots.prmproject.exceptions.ForbiddenException;
import com.pussinboots.prmproject.services.otp.OtpGenarateService;

import lombok.Builder;

@Service
@Builder
public class OtpGenarateServiceImpl implements OtpGenarateService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String genarateOtp(String email) {
        Optional<Staff> staffOtp = staffRepository.findByEmail(email);
        Optional<Customer> customerOtp = customerRepository.findByEmail(email);
        if (staffOtp.isEmpty() && customerOtp.isEmpty()) {
            throw new RuntimeException("Cannot found user with email " + email);
        }
        Random random = new Random();
        int otpLength = 6;
        String numbers = "0123456789";
        StringBuilder sb = new StringBuilder();
        LocalDateTime currenDateTime = LocalDateTime.now();
        for (int i = 0; i < otpLength; i++) {
            sb.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        if (!customerOtp.isEmpty()) {
            Customer customer = customerOtp.get();
            customer.setOtp(sb.toString());
            customer.setCreateTokenDate(currenDateTime);
            customerRepository.save(customer);
        }
        if (!staffOtp.isEmpty()) {
            Staff staff = staffOtp.get();
            staff.setOtp(sb.toString());
            staff.setCreateTokenDate(currenDateTime);
            staffRepository.save(staff);
        }
        return sb.toString();
    }

    @Override
    public Boolean validationOtp(String email, String otp) {
        System.out.println("Validation");
        Optional<Staff> staffOtp = staffRepository.findByEmail(email);
        Optional<Customer> customerOtp = customerRepository.findByEmail(email);
        LocalDateTime currenDateTime = LocalDateTime.now();
        Boolean flag = false;
        Long validTime = 60l;
        if (staffOtp.isEmpty() && customerOtp.isEmpty()) {
            System.out.println("User empty");
            throw new RuntimeException("Cannot found user with email " + email);
        }
        if (!customerOtp.isEmpty()) {
            Customer customer = customerOtp.get();
            LocalDateTime optCreateDate = customer.getCreateTokenDate();
            if(currenDateTime.compareTo(optCreateDate.plusSeconds(validTime)) > 0){
                System.out.println("Otp out of time");
                throw new ForbiddenException("Otp code is out of time");
            }
            if(!customer.getOtp().equals(otp)){
                System.out.println("Otp Not Match");
                throw new ForbiddenException("Otp code did not match");
            }
            if (customer.getOtp().equals(otp) &&
                    currenDateTime.compareTo(optCreateDate.plusSeconds(validTime)) <= 0) {
                flag = true;
            }
        }
        if (!staffOtp.isEmpty()) {
            Staff staff = staffOtp.get();
            LocalDateTime optCreateDate = staff.getCreateTokenDate();
            if(currenDateTime.compareTo(optCreateDate.plusSeconds(validTime)) > 0){
                System.out.println("Otp out of time");
                throw new ForbiddenException("Otp code is out of time");
            }
            if(!staff.getOtp().equals(otp)){
                System.out.println("Otp Not Match");
                throw new ForbiddenException("Otp code did not match");
            }
            if (staff.getOtp().equals(otp) &&
                    currenDateTime.compareTo(optCreateDate.plusSeconds(validTime)) <= 0) {
                flag = true;
            }
        }
        return flag;
    }

}

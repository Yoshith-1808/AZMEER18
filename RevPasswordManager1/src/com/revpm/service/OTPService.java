package com.revpm.service;

import com.revpm.dao.VerificationCodeDAO;
import com.revpm.model.VerificationCode;
import com.revpm.util.OTPUtil;

import java.time.LocalDateTime;
import java.util.Optional;

public class OTPService {

    private final VerificationCodeDAO codeDAO = new VerificationCodeDAO();

    // Generate and store OTP (valid for 5 minutes)
    public String generateOTP(int userId) {
        String otp = OTPUtil.generateOTP(6);
        VerificationCode code = new VerificationCode(userId, otp, LocalDateTime.now().plusMinutes(5));
        codeDAO.addCode(code);
        return otp;
    }

    // Verify OTP
    public boolean verifyOTP(int userId, String inputOtp) {
        Optional<VerificationCode> codeOpt = codeDAO.getLatestCodeByUserId(userId);
        if (codeOpt.isPresent()) {
            VerificationCode code = codeOpt.get();
            if (code.getExpirationTime().isAfter(LocalDateTime.now())
                    && code.getCode().equals(inputOtp)) {
                return true;
            }
        }
        return false;
    }
}

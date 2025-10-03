package vn.iotstar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.iotstar.model.UserInfo;
import vn.iotstar.repository.UserInfoRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "Thêm user thành công!";
    }
}



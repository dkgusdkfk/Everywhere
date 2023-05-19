package com.ssafy.enjoytrip.user.model.service;


import com.ssafy.enjoytrip.user.model.dao.UserDao;
import com.ssafy.enjoytrip.user.model.dto.FindPasswordRequest;
import com.ssafy.enjoytrip.user.model.dto.LoginRequest;
import com.ssafy.enjoytrip.user.model.dto.User;
import com.ssafy.enjoytrip.user.model.dto.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserDao userDao;
    private final SqlSession sqlSession;

    public User login(LoginRequest request) {
        try {
            User user = userDao.search(request.getId());
            if (user == null)
                throw new UserException("등록되지 않은 아이디입니다.");

            if (!user.getPassword().equals(request.getPassword()))
                throw new UserException("비밀번호가 일치하지 않습니다");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("로그인 처리 중 오류 발생");
        }
    }

    @Override
    public User search(String id) {
        try {
            User user = userDao.search(id);
            if (user == null) throw new UserException("없는 사용자입니다");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("유저 search중 오류 발생");
        }
    }

    @Override
    public void register(User user) {
        try {
            User find = userDao.search(user.getId());
            if (find != null) {
                throw new UserException("이미 등록된 아이디 입니다.");
            }
            userDao.register(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("회원 정보 처리 중 오류 발생!!!");
        }
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserException("회원 정보 수정 중 오류 발생!!");
        }
    }

    @Override
    public void remove(String id) {
        try {
            userDao.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserException("회원 탈퇴 중 오류 발생!!");
        }
    }


    @Override
    public User findPassword(FindPasswordRequest request) {
        try {
            return userDao.findPassword(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserException("비밀 번호 찾기 중 오류 발생!!");
        }
    }

    @Override
    public int idCheck(String id) {
        try {
            if (userDao.search(id) == null)
                return 0;
            else
                return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("id 중복 체크 중 오류 발생!!");
        }
    }

    @Override
    public void saveRefreshToken(String id, String refreshToken) throws Exception {
        Map<String, String> map = new HashMap<>();
        log.debug("service saveRefreshToken: id:{}", id);
        log.debug("service saveRefreshToken: refreshToken:{}", refreshToken);
        map.put("id", id);
        map.put("token", refreshToken);
        sqlSession.getMapper(UserDao.class).saveRefreshToken(map);
    }

    @Override
    public Object getRefreshToken(String id) throws Exception {
        return sqlSession.getMapper(UserDao.class).getRefreshToken(id);
    }

    @Override
    public void deleRefreshToken(String id) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("token", null);
        sqlSession.getMapper(UserDao.class).deleteRefreshToken(map);
    }
}

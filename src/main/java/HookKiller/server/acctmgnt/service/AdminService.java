package HookKiller.server.acctmgnt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    /**
     * 관리자 계정 등록
     */
    @Transactional
    public void regAdmin() {

    }

    /**
     * 관리자 계정 리스트
     */
    @Transactional(readOnly = true)
    public void adminList() {

    }

    /**
     * 관리자 계정 삭제
     */


    /**
     * 사용자 계정 등록
     */


    /**
     * 사용자 계정 리스트
     */


    /**
     *
     * 사용자 계정 삭제
     */


}

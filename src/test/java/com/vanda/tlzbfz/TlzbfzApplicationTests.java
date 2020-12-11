package com.vanda.tlzbfz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TlzbfzApplicationTests {

    @Test
    void contextLoads() {
        String id = String.valueOf(System.currentTimeMillis());
        System.out.println(id);
        System.out.println(id.substring(6,id.length()));
        //record.setRwbh(id.substring(id.lastIndexOf(0,2)));
    }

}

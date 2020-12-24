package com.vanda.tlzbfz.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName TXclrExtenBean
 * @Description: TODO
 * @Author onion
 * @Date 2020/12/18
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
public class TXclrExtenBean extends TXclr{
    private List<TDbyh> tDbyhs;
}

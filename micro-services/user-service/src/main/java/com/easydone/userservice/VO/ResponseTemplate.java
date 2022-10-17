package com.easydone.userservice.VO;

import com.easydone.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {
    private String username;
    private Long userId;
    private Boolean isValid;

}

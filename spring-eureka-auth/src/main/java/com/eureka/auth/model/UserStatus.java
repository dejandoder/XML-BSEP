package com.eureka.auth.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "UserStatus")
@XmlEnum
public enum UserStatus {
	NOT_ACTIVATED, ACTIVATED, BLOCKED;
}

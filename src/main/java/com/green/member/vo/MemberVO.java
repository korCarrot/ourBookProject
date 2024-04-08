package com.green.member.vo;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

    @Component("memberVO")
    @Log4j2
    public class MemberVO {
        private String member_id;
        private String member_pw;
        private String member_name;
        private String member_gender;
        private String tel;
        private String hp;
        private String smssts_yn;
        private String email;
        private String emailsts_yn;
        private String zipcode;
        private String roadAddress;
        private String jibunAddress;
        private String member_birth_y;
        private String member_birth_m;
        private String member_birth_d;
        private String member_birth_sl;
        private String joinDate;
        private String del_yn;
        private String detailAddress;

        public MemberVO(){
            log.info("MemberVO실행");
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMember_pw() {
            return member_pw;
        }

        public void setMember_pw(String member_pw) {
            this.member_pw = member_pw;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getMember_gender() {
            return member_gender;
        }

        public void setMember_gender(String member_gender) {
            this.member_gender = member_gender;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getHp() {
            return hp;
        }

        public void setHp(String hp) {
            this.hp = hp;
        }

        public String getSmssts_yn() {
            return smssts_yn;
        }

        public void setSmssts_yn(String smssts_yn) {
            this.smssts_yn = smssts_yn;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmailsts_yn() {
            return emailsts_yn;
        }

        public void setEmailsts_yn(String emailsts_yn) {
            this.emailsts_yn = emailsts_yn;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getRoadAddress() {
            return roadAddress;
        }

        public void setRoadAddress(String roadAddress) {
            this.roadAddress = roadAddress;
        }

        public String getJibunAddress() {
            return jibunAddress;
        }

        public void setJibunAddress(String jibunAddress) {
            this.jibunAddress = jibunAddress;
        }

        public String getMember_birth_y() {
            return member_birth_y;
        }

        public void setMember_birth_y(String member_birth_y) {
            this.member_birth_y = member_birth_y;
        }

        public String getMember_birth_m() {
            return member_birth_m;
        }

        public void setMember_birth_m(String member_birth_m) {
            this.member_birth_m = member_birth_m;
        }

        public String getMember_birth_d() {
            return member_birth_d;
        }

        public void setMember_birth_d(String member_birth_d) {
            this.member_birth_d = member_birth_d;
        }

        public String getMember_birth_sl() {
            return member_birth_sl;
        }

        public void setMember_birth_sl(String member_birth_sl) {
            this.member_birth_sl = member_birth_sl;
        }

        public String getJoinDate() {
            return joinDate;
        }

        public void setJoinDate(String joinDate) {
            this.joinDate = joinDate;
        }

        public String getDel_yn() {
            return del_yn;
        }

        public void setDel_yn(String del_yn) {
            this.del_yn = del_yn;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }
    }

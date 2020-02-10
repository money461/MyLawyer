package com.tz.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZaComAuthenticationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZaComAuthenticationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andLawIdIsNull() {
            addCriterion("law_id is null");
            return (Criteria) this;
        }

        public Criteria andLawIdIsNotNull() {
            addCriterion("law_id is not null");
            return (Criteria) this;
        }

        public Criteria andLawIdEqualTo(String value) {
            addCriterion("law_id =", value, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdNotEqualTo(String value) {
            addCriterion("law_id <>", value, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdGreaterThan(String value) {
            addCriterion("law_id >", value, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdGreaterThanOrEqualTo(String value) {
            addCriterion("law_id >=", value, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdLessThan(String value) {
            addCriterion("law_id <", value, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdLessThanOrEqualTo(String value) {
            addCriterion("law_id <=", value, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdLike(String value) {
            addCriterion("law_id like", value, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdNotLike(String value) {
            addCriterion("law_id not like", value, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdIn(List<String> values) {
            addCriterion("law_id in", values, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdNotIn(List<String> values) {
            addCriterion("law_id not in", values, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdBetween(String value1, String value2) {
            addCriterion("law_id between", value1, value2, "lawId");
            return (Criteria) this;
        }

        public Criteria andLawIdNotBetween(String value1, String value2) {
            addCriterion("law_id not between", value1, value2, "lawId");
            return (Criteria) this;
        }

        public Criteria andComNicknameIsNull() {
            addCriterion("com_nickname is null");
            return (Criteria) this;
        }

        public Criteria andComNicknameIsNotNull() {
            addCriterion("com_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andComNicknameEqualTo(String value) {
            addCriterion("com_nickname =", value, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameNotEqualTo(String value) {
            addCriterion("com_nickname <>", value, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameGreaterThan(String value) {
            addCriterion("com_nickname >", value, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("com_nickname >=", value, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameLessThan(String value) {
            addCriterion("com_nickname <", value, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameLessThanOrEqualTo(String value) {
            addCriterion("com_nickname <=", value, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameLike(String value) {
            addCriterion("com_nickname like", value, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameNotLike(String value) {
            addCriterion("com_nickname not like", value, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameIn(List<String> values) {
            addCriterion("com_nickname in", values, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameNotIn(List<String> values) {
            addCriterion("com_nickname not in", values, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameBetween(String value1, String value2) {
            addCriterion("com_nickname between", value1, value2, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNicknameNotBetween(String value1, String value2) {
            addCriterion("com_nickname not between", value1, value2, "comNickname");
            return (Criteria) this;
        }

        public Criteria andComNameIsNull() {
            addCriterion("com_name is null");
            return (Criteria) this;
        }

        public Criteria andComNameIsNotNull() {
            addCriterion("com_name is not null");
            return (Criteria) this;
        }

        public Criteria andComNameEqualTo(String value) {
            addCriterion("com_name =", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameNotEqualTo(String value) {
            addCriterion("com_name <>", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameGreaterThan(String value) {
            addCriterion("com_name >", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameGreaterThanOrEqualTo(String value) {
            addCriterion("com_name >=", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameLessThan(String value) {
            addCriterion("com_name <", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameLessThanOrEqualTo(String value) {
            addCriterion("com_name <=", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameLike(String value) {
            addCriterion("com_name like", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameNotLike(String value) {
            addCriterion("com_name not like", value, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameIn(List<String> values) {
            addCriterion("com_name in", values, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameNotIn(List<String> values) {
            addCriterion("com_name not in", values, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameBetween(String value1, String value2) {
            addCriterion("com_name between", value1, value2, "comName");
            return (Criteria) this;
        }

        public Criteria andComNameNotBetween(String value1, String value2) {
            addCriterion("com_name not between", value1, value2, "comName");
            return (Criteria) this;
        }

        public Criteria andComManagerIsNull() {
            addCriterion("com_manager is null");
            return (Criteria) this;
        }

        public Criteria andComManagerIsNotNull() {
            addCriterion("com_manager is not null");
            return (Criteria) this;
        }

        public Criteria andComManagerEqualTo(String value) {
            addCriterion("com_manager =", value, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerNotEqualTo(String value) {
            addCriterion("com_manager <>", value, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerGreaterThan(String value) {
            addCriterion("com_manager >", value, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerGreaterThanOrEqualTo(String value) {
            addCriterion("com_manager >=", value, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerLessThan(String value) {
            addCriterion("com_manager <", value, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerLessThanOrEqualTo(String value) {
            addCriterion("com_manager <=", value, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerLike(String value) {
            addCriterion("com_manager like", value, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerNotLike(String value) {
            addCriterion("com_manager not like", value, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerIn(List<String> values) {
            addCriterion("com_manager in", values, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerNotIn(List<String> values) {
            addCriterion("com_manager not in", values, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerBetween(String value1, String value2) {
            addCriterion("com_manager between", value1, value2, "comManager");
            return (Criteria) this;
        }

        public Criteria andComManagerNotBetween(String value1, String value2) {
            addCriterion("com_manager not between", value1, value2, "comManager");
            return (Criteria) this;
        }

        public Criteria andComPhoneIsNull() {
            addCriterion("com_phone is null");
            return (Criteria) this;
        }

        public Criteria andComPhoneIsNotNull() {
            addCriterion("com_phone is not null");
            return (Criteria) this;
        }

        public Criteria andComPhoneEqualTo(String value) {
            addCriterion("com_phone =", value, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneNotEqualTo(String value) {
            addCriterion("com_phone <>", value, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneGreaterThan(String value) {
            addCriterion("com_phone >", value, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("com_phone >=", value, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneLessThan(String value) {
            addCriterion("com_phone <", value, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneLessThanOrEqualTo(String value) {
            addCriterion("com_phone <=", value, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneLike(String value) {
            addCriterion("com_phone like", value, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneNotLike(String value) {
            addCriterion("com_phone not like", value, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneIn(List<String> values) {
            addCriterion("com_phone in", values, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneNotIn(List<String> values) {
            addCriterion("com_phone not in", values, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneBetween(String value1, String value2) {
            addCriterion("com_phone between", value1, value2, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComPhoneNotBetween(String value1, String value2) {
            addCriterion("com_phone not between", value1, value2, "comPhone");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonIsNull() {
            addCriterion("com_legal_person is null");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonIsNotNull() {
            addCriterion("com_legal_person is not null");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonEqualTo(String value) {
            addCriterion("com_legal_person =", value, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonNotEqualTo(String value) {
            addCriterion("com_legal_person <>", value, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonGreaterThan(String value) {
            addCriterion("com_legal_person >", value, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonGreaterThanOrEqualTo(String value) {
            addCriterion("com_legal_person >=", value, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonLessThan(String value) {
            addCriterion("com_legal_person <", value, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonLessThanOrEqualTo(String value) {
            addCriterion("com_legal_person <=", value, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonLike(String value) {
            addCriterion("com_legal_person like", value, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonNotLike(String value) {
            addCriterion("com_legal_person not like", value, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonIn(List<String> values) {
            addCriterion("com_legal_person in", values, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonNotIn(List<String> values) {
            addCriterion("com_legal_person not in", values, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonBetween(String value1, String value2) {
            addCriterion("com_legal_person between", value1, value2, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andComLegalPersonNotBetween(String value1, String value2) {
            addCriterion("com_legal_person not between", value1, value2, "comLegalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneIsNull() {
            addCriterion("legal_person_phone is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneIsNotNull() {
            addCriterion("legal_person_phone is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneEqualTo(String value) {
            addCriterion("legal_person_phone =", value, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneNotEqualTo(String value) {
            addCriterion("legal_person_phone <>", value, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneGreaterThan(String value) {
            addCriterion("legal_person_phone >", value, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person_phone >=", value, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneLessThan(String value) {
            addCriterion("legal_person_phone <", value, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneLessThanOrEqualTo(String value) {
            addCriterion("legal_person_phone <=", value, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneLike(String value) {
            addCriterion("legal_person_phone like", value, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneNotLike(String value) {
            addCriterion("legal_person_phone not like", value, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneIn(List<String> values) {
            addCriterion("legal_person_phone in", values, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneNotIn(List<String> values) {
            addCriterion("legal_person_phone not in", values, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneBetween(String value1, String value2) {
            addCriterion("legal_person_phone between", value1, value2, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andLegalPersonPhoneNotBetween(String value1, String value2) {
            addCriterion("legal_person_phone not between", value1, value2, "legalPersonPhone");
            return (Criteria) this;
        }

        public Criteria andComIntroduceIsNull() {
            addCriterion("com_introduce is null");
            return (Criteria) this;
        }

        public Criteria andComIntroduceIsNotNull() {
            addCriterion("com_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andComIntroduceEqualTo(String value) {
            addCriterion("com_introduce =", value, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceNotEqualTo(String value) {
            addCriterion("com_introduce <>", value, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceGreaterThan(String value) {
            addCriterion("com_introduce >", value, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("com_introduce >=", value, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceLessThan(String value) {
            addCriterion("com_introduce <", value, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceLessThanOrEqualTo(String value) {
            addCriterion("com_introduce <=", value, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceLike(String value) {
            addCriterion("com_introduce like", value, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceNotLike(String value) {
            addCriterion("com_introduce not like", value, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceIn(List<String> values) {
            addCriterion("com_introduce in", values, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceNotIn(List<String> values) {
            addCriterion("com_introduce not in", values, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceBetween(String value1, String value2) {
            addCriterion("com_introduce between", value1, value2, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComIntroduceNotBetween(String value1, String value2) {
            addCriterion("com_introduce not between", value1, value2, "comIntroduce");
            return (Criteria) this;
        }

        public Criteria andComStatusIsNull() {
            addCriterion("com_status is null");
            return (Criteria) this;
        }

        public Criteria andComStatusIsNotNull() {
            addCriterion("com_status is not null");
            return (Criteria) this;
        }

        public Criteria andComStatusEqualTo(Integer value) {
            addCriterion("com_status =", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusNotEqualTo(Integer value) {
            addCriterion("com_status <>", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusGreaterThan(Integer value) {
            addCriterion("com_status >", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("com_status >=", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusLessThan(Integer value) {
            addCriterion("com_status <", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusLessThanOrEqualTo(Integer value) {
            addCriterion("com_status <=", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusIn(List<Integer> values) {
            addCriterion("com_status in", values, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusNotIn(List<Integer> values) {
            addCriterion("com_status not in", values, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusBetween(Integer value1, Integer value2) {
            addCriterion("com_status between", value1, value2, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("com_status not between", value1, value2, "comStatus");
            return (Criteria) this;
        }

        public Criteria andLawEnsureIsNull() {
            addCriterion("law_ensure is null");
            return (Criteria) this;
        }

        public Criteria andLawEnsureIsNotNull() {
            addCriterion("law_ensure is not null");
            return (Criteria) this;
        }

        public Criteria andLawEnsureEqualTo(Integer value) {
            addCriterion("law_ensure =", value, "lawEnsure");
            return (Criteria) this;
        }

        public Criteria andLawEnsureNotEqualTo(Integer value) {
            addCriterion("law_ensure <>", value, "lawEnsure");
            return (Criteria) this;
        }

        public Criteria andLawEnsureGreaterThan(Integer value) {
            addCriterion("law_ensure >", value, "lawEnsure");
            return (Criteria) this;
        }

        public Criteria andLawEnsureGreaterThanOrEqualTo(Integer value) {
            addCriterion("law_ensure >=", value, "lawEnsure");
            return (Criteria) this;
        }

        public Criteria andLawEnsureLessThan(Integer value) {
            addCriterion("law_ensure <", value, "lawEnsure");
            return (Criteria) this;
        }

        public Criteria andLawEnsureLessThanOrEqualTo(Integer value) {
            addCriterion("law_ensure <=", value, "lawEnsure");
            return (Criteria) this;
        }

        public Criteria andLawEnsureIn(List<Integer> values) {
            addCriterion("law_ensure in", values, "lawEnsure");
            return (Criteria) this;
        }

        public Criteria andLawEnsureNotIn(List<Integer> values) {
            addCriterion("law_ensure not in", values, "lawEnsure");
            return (Criteria) this;
        }

        public Criteria andLawEnsureBetween(Integer value1, Integer value2) {
            addCriterion("law_ensure between", value1, value2, "lawEnsure");
            return (Criteria) this;
        }

        public Criteria andLawEnsureNotBetween(Integer value1, Integer value2) {
            addCriterion("law_ensure not between", value1, value2, "lawEnsure");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andComAddressIsNull() {
            addCriterion("com_address is null");
            return (Criteria) this;
        }

        public Criteria andComAddressIsNotNull() {
            addCriterion("com_address is not null");
            return (Criteria) this;
        }

        public Criteria andComAddressEqualTo(String value) {
            addCriterion("com_address =", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressNotEqualTo(String value) {
            addCriterion("com_address <>", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressGreaterThan(String value) {
            addCriterion("com_address >", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressGreaterThanOrEqualTo(String value) {
            addCriterion("com_address >=", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressLessThan(String value) {
            addCriterion("com_address <", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressLessThanOrEqualTo(String value) {
            addCriterion("com_address <=", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressLike(String value) {
            addCriterion("com_address like", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressNotLike(String value) {
            addCriterion("com_address not like", value, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressIn(List<String> values) {
            addCriterion("com_address in", values, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressNotIn(List<String> values) {
            addCriterion("com_address not in", values, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressBetween(String value1, String value2) {
            addCriterion("com_address between", value1, value2, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComAddressNotBetween(String value1, String value2) {
            addCriterion("com_address not between", value1, value2, "comAddress");
            return (Criteria) this;
        }

        public Criteria andComLonIsNull() {
            addCriterion("com_lon is null");
            return (Criteria) this;
        }

        public Criteria andComLonIsNotNull() {
            addCriterion("com_lon is not null");
            return (Criteria) this;
        }

        public Criteria andComLonEqualTo(Double value) {
            addCriterion("com_lon =", value, "comLon");
            return (Criteria) this;
        }

        public Criteria andComLonNotEqualTo(Double value) {
            addCriterion("com_lon <>", value, "comLon");
            return (Criteria) this;
        }

        public Criteria andComLonGreaterThan(Double value) {
            addCriterion("com_lon >", value, "comLon");
            return (Criteria) this;
        }

        public Criteria andComLonGreaterThanOrEqualTo(Double value) {
            addCriterion("com_lon >=", value, "comLon");
            return (Criteria) this;
        }

        public Criteria andComLonLessThan(Double value) {
            addCriterion("com_lon <", value, "comLon");
            return (Criteria) this;
        }

        public Criteria andComLonLessThanOrEqualTo(Double value) {
            addCriterion("com_lon <=", value, "comLon");
            return (Criteria) this;
        }

        public Criteria andComLonIn(List<Double> values) {
            addCriterion("com_lon in", values, "comLon");
            return (Criteria) this;
        }

        public Criteria andComLonNotIn(List<Double> values) {
            addCriterion("com_lon not in", values, "comLon");
            return (Criteria) this;
        }

        public Criteria andComLonBetween(Double value1, Double value2) {
            addCriterion("com_lon between", value1, value2, "comLon");
            return (Criteria) this;
        }

        public Criteria andComLonNotBetween(Double value1, Double value2) {
            addCriterion("com_lon not between", value1, value2, "comLon");
            return (Criteria) this;
        }

        public Criteria andComLatIsNull() {
            addCriterion("com_lat is null");
            return (Criteria) this;
        }

        public Criteria andComLatIsNotNull() {
            addCriterion("com_lat is not null");
            return (Criteria) this;
        }

        public Criteria andComLatEqualTo(Double value) {
            addCriterion("com_lat =", value, "comLat");
            return (Criteria) this;
        }

        public Criteria andComLatNotEqualTo(Double value) {
            addCriterion("com_lat <>", value, "comLat");
            return (Criteria) this;
        }

        public Criteria andComLatGreaterThan(Double value) {
            addCriterion("com_lat >", value, "comLat");
            return (Criteria) this;
        }

        public Criteria andComLatGreaterThanOrEqualTo(Double value) {
            addCriterion("com_lat >=", value, "comLat");
            return (Criteria) this;
        }

        public Criteria andComLatLessThan(Double value) {
            addCriterion("com_lat <", value, "comLat");
            return (Criteria) this;
        }

        public Criteria andComLatLessThanOrEqualTo(Double value) {
            addCriterion("com_lat <=", value, "comLat");
            return (Criteria) this;
        }

        public Criteria andComLatIn(List<Double> values) {
            addCriterion("com_lat in", values, "comLat");
            return (Criteria) this;
        }

        public Criteria andComLatNotIn(List<Double> values) {
            addCriterion("com_lat not in", values, "comLat");
            return (Criteria) this;
        }

        public Criteria andComLatBetween(Double value1, Double value2) {
            addCriterion("com_lat between", value1, value2, "comLat");
            return (Criteria) this;
        }

        public Criteria andComLatNotBetween(Double value1, Double value2) {
            addCriterion("com_lat not between", value1, value2, "comLat");
            return (Criteria) this;
        }

        public Criteria andComLogoIsNull() {
            addCriterion("com_logo is null");
            return (Criteria) this;
        }

        public Criteria andComLogoIsNotNull() {
            addCriterion("com_logo is not null");
            return (Criteria) this;
        }

        public Criteria andComLogoEqualTo(String value) {
            addCriterion("com_logo =", value, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoNotEqualTo(String value) {
            addCriterion("com_logo <>", value, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoGreaterThan(String value) {
            addCriterion("com_logo >", value, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoGreaterThanOrEqualTo(String value) {
            addCriterion("com_logo >=", value, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoLessThan(String value) {
            addCriterion("com_logo <", value, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoLessThanOrEqualTo(String value) {
            addCriterion("com_logo <=", value, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoLike(String value) {
            addCriterion("com_logo like", value, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoNotLike(String value) {
            addCriterion("com_logo not like", value, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoIn(List<String> values) {
            addCriterion("com_logo in", values, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoNotIn(List<String> values) {
            addCriterion("com_logo not in", values, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoBetween(String value1, String value2) {
            addCriterion("com_logo between", value1, value2, "comLogo");
            return (Criteria) this;
        }

        public Criteria andComLogoNotBetween(String value1, String value2) {
            addCriterion("com_logo not between", value1, value2, "comLogo");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlIsNull() {
            addCriterion("licence_url is null");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlIsNotNull() {
            addCriterion("licence_url is not null");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlEqualTo(String value) {
            addCriterion("licence_url =", value, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlNotEqualTo(String value) {
            addCriterion("licence_url <>", value, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlGreaterThan(String value) {
            addCriterion("licence_url >", value, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlGreaterThanOrEqualTo(String value) {
            addCriterion("licence_url >=", value, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlLessThan(String value) {
            addCriterion("licence_url <", value, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlLessThanOrEqualTo(String value) {
            addCriterion("licence_url <=", value, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlLike(String value) {
            addCriterion("licence_url like", value, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlNotLike(String value) {
            addCriterion("licence_url not like", value, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlIn(List<String> values) {
            addCriterion("licence_url in", values, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlNotIn(List<String> values) {
            addCriterion("licence_url not in", values, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlBetween(String value1, String value2) {
            addCriterion("licence_url between", value1, value2, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andLicenceUrlNotBetween(String value1, String value2) {
            addCriterion("licence_url not between", value1, value2, "licenceUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlIsNull() {
            addCriterion("qualification_url is null");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlIsNotNull() {
            addCriterion("qualification_url is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlEqualTo(String value) {
            addCriterion("qualification_url =", value, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlNotEqualTo(String value) {
            addCriterion("qualification_url <>", value, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlGreaterThan(String value) {
            addCriterion("qualification_url >", value, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlGreaterThanOrEqualTo(String value) {
            addCriterion("qualification_url >=", value, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlLessThan(String value) {
            addCriterion("qualification_url <", value, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlLessThanOrEqualTo(String value) {
            addCriterion("qualification_url <=", value, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlLike(String value) {
            addCriterion("qualification_url like", value, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlNotLike(String value) {
            addCriterion("qualification_url not like", value, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlIn(List<String> values) {
            addCriterion("qualification_url in", values, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlNotIn(List<String> values) {
            addCriterion("qualification_url not in", values, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlBetween(String value1, String value2) {
            addCriterion("qualification_url between", value1, value2, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationUrlNotBetween(String value1, String value2) {
            addCriterion("qualification_url not between", value1, value2, "qualificationUrl");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("ID_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("ID_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("ID_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("ID_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("ID_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("ID_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("ID_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("ID_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("ID_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("ID_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("ID_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("ID_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("ID_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("ID_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andExamineTimeIsNull() {
            addCriterion("examine_time is null");
            return (Criteria) this;
        }

        public Criteria andExamineTimeIsNotNull() {
            addCriterion("examine_time is not null");
            return (Criteria) this;
        }

        public Criteria andExamineTimeEqualTo(Date value) {
            addCriterion("examine_time =", value, "examineTime");
            return (Criteria) this;
        }

        public Criteria andExamineTimeNotEqualTo(Date value) {
            addCriterion("examine_time <>", value, "examineTime");
            return (Criteria) this;
        }

        public Criteria andExamineTimeGreaterThan(Date value) {
            addCriterion("examine_time >", value, "examineTime");
            return (Criteria) this;
        }

        public Criteria andExamineTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("examine_time >=", value, "examineTime");
            return (Criteria) this;
        }

        public Criteria andExamineTimeLessThan(Date value) {
            addCriterion("examine_time <", value, "examineTime");
            return (Criteria) this;
        }

        public Criteria andExamineTimeLessThanOrEqualTo(Date value) {
            addCriterion("examine_time <=", value, "examineTime");
            return (Criteria) this;
        }

        public Criteria andExamineTimeIn(List<Date> values) {
            addCriterion("examine_time in", values, "examineTime");
            return (Criteria) this;
        }

        public Criteria andExamineTimeNotIn(List<Date> values) {
            addCriterion("examine_time not in", values, "examineTime");
            return (Criteria) this;
        }

        public Criteria andExamineTimeBetween(Date value1, Date value2) {
            addCriterion("examine_time between", value1, value2, "examineTime");
            return (Criteria) this;
        }

        public Criteria andExamineTimeNotBetween(Date value1, Date value2) {
            addCriterion("examine_time not between", value1, value2, "examineTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andExamineIdIsNull() {
            addCriterion("examine_id is null");
            return (Criteria) this;
        }

        public Criteria andExamineIdIsNotNull() {
            addCriterion("examine_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamineIdEqualTo(String value) {
            addCriterion("examine_id =", value, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdNotEqualTo(String value) {
            addCriterion("examine_id <>", value, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdGreaterThan(String value) {
            addCriterion("examine_id >", value, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdGreaterThanOrEqualTo(String value) {
            addCriterion("examine_id >=", value, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdLessThan(String value) {
            addCriterion("examine_id <", value, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdLessThanOrEqualTo(String value) {
            addCriterion("examine_id <=", value, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdLike(String value) {
            addCriterion("examine_id like", value, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdNotLike(String value) {
            addCriterion("examine_id not like", value, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdIn(List<String> values) {
            addCriterion("examine_id in", values, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdNotIn(List<String> values) {
            addCriterion("examine_id not in", values, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdBetween(String value1, String value2) {
            addCriterion("examine_id between", value1, value2, "examineId");
            return (Criteria) this;
        }

        public Criteria andExamineIdNotBetween(String value1, String value2) {
            addCriterion("examine_id not between", value1, value2, "examineId");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
package com.tz.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZaUserPublishCaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZaUserPublishCaseExample() {
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

        public Criteria andAwardIdIsNull() {
            addCriterion("award_id is null");
            return (Criteria) this;
        }

        public Criteria andAwardIdIsNotNull() {
            addCriterion("award_id is not null");
            return (Criteria) this;
        }

        public Criteria andAwardIdEqualTo(String value) {
            addCriterion("award_id =", value, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdNotEqualTo(String value) {
            addCriterion("award_id <>", value, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdGreaterThan(String value) {
            addCriterion("award_id >", value, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdGreaterThanOrEqualTo(String value) {
            addCriterion("award_id >=", value, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdLessThan(String value) {
            addCriterion("award_id <", value, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdLessThanOrEqualTo(String value) {
            addCriterion("award_id <=", value, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdLike(String value) {
            addCriterion("award_id like", value, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdNotLike(String value) {
            addCriterion("award_id not like", value, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdIn(List<String> values) {
            addCriterion("award_id in", values, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdNotIn(List<String> values) {
            addCriterion("award_id not in", values, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdBetween(String value1, String value2) {
            addCriterion("award_id between", value1, value2, "awardId");
            return (Criteria) this;
        }

        public Criteria andAwardIdNotBetween(String value1, String value2) {
            addCriterion("award_id not between", value1, value2, "awardId");
            return (Criteria) this;
        }

        public Criteria andCaseIdIsNull() {
            addCriterion("case_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseIdIsNotNull() {
            addCriterion("case_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseIdEqualTo(Integer value) {
            addCriterion("case_id =", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotEqualTo(Integer value) {
            addCriterion("case_id <>", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThan(Integer value) {
            addCriterion("case_id >", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_id >=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThan(Integer value) {
            addCriterion("case_id <", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_id <=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdIn(List<Integer> values) {
            addCriterion("case_id in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotIn(List<Integer> values) {
            addCriterion("case_id not in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdBetween(Integer value1, Integer value2) {
            addCriterion("case_id between", value1, value2, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_id not between", value1, value2, "caseId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentDescIsNull() {
            addCriterion("content_desc is null");
            return (Criteria) this;
        }

        public Criteria andContentDescIsNotNull() {
            addCriterion("content_desc is not null");
            return (Criteria) this;
        }

        public Criteria andContentDescEqualTo(String value) {
            addCriterion("content_desc =", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescNotEqualTo(String value) {
            addCriterion("content_desc <>", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescGreaterThan(String value) {
            addCriterion("content_desc >", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescGreaterThanOrEqualTo(String value) {
            addCriterion("content_desc >=", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescLessThan(String value) {
            addCriterion("content_desc <", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescLessThanOrEqualTo(String value) {
            addCriterion("content_desc <=", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescLike(String value) {
            addCriterion("content_desc like", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescNotLike(String value) {
            addCriterion("content_desc not like", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescIn(List<String> values) {
            addCriterion("content_desc in", values, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescNotIn(List<String> values) {
            addCriterion("content_desc not in", values, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescBetween(String value1, String value2) {
            addCriterion("content_desc between", value1, value2, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescNotBetween(String value1, String value2) {
            addCriterion("content_desc not between", value1, value2, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCaseAddressIsNull() {
            addCriterion("case_address is null");
            return (Criteria) this;
        }

        public Criteria andCaseAddressIsNotNull() {
            addCriterion("case_address is not null");
            return (Criteria) this;
        }

        public Criteria andCaseAddressEqualTo(String value) {
            addCriterion("case_address =", value, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressNotEqualTo(String value) {
            addCriterion("case_address <>", value, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressGreaterThan(String value) {
            addCriterion("case_address >", value, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressGreaterThanOrEqualTo(String value) {
            addCriterion("case_address >=", value, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressLessThan(String value) {
            addCriterion("case_address <", value, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressLessThanOrEqualTo(String value) {
            addCriterion("case_address <=", value, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressLike(String value) {
            addCriterion("case_address like", value, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressNotLike(String value) {
            addCriterion("case_address not like", value, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressIn(List<String> values) {
            addCriterion("case_address in", values, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressNotIn(List<String> values) {
            addCriterion("case_address not in", values, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressBetween(String value1, String value2) {
            addCriterion("case_address between", value1, value2, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseAddressNotBetween(String value1, String value2) {
            addCriterion("case_address not between", value1, value2, "caseAddress");
            return (Criteria) this;
        }

        public Criteria andCaseLonIsNull() {
            addCriterion("case_lon is null");
            return (Criteria) this;
        }

        public Criteria andCaseLonIsNotNull() {
            addCriterion("case_lon is not null");
            return (Criteria) this;
        }

        public Criteria andCaseLonEqualTo(Double value) {
            addCriterion("case_lon =", value, "caseLon");
            return (Criteria) this;
        }

        public Criteria andCaseLonNotEqualTo(Double value) {
            addCriterion("case_lon <>", value, "caseLon");
            return (Criteria) this;
        }

        public Criteria andCaseLonGreaterThan(Double value) {
            addCriterion("case_lon >", value, "caseLon");
            return (Criteria) this;
        }

        public Criteria andCaseLonGreaterThanOrEqualTo(Double value) {
            addCriterion("case_lon >=", value, "caseLon");
            return (Criteria) this;
        }

        public Criteria andCaseLonLessThan(Double value) {
            addCriterion("case_lon <", value, "caseLon");
            return (Criteria) this;
        }

        public Criteria andCaseLonLessThanOrEqualTo(Double value) {
            addCriterion("case_lon <=", value, "caseLon");
            return (Criteria) this;
        }

        public Criteria andCaseLonIn(List<Double> values) {
            addCriterion("case_lon in", values, "caseLon");
            return (Criteria) this;
        }

        public Criteria andCaseLonNotIn(List<Double> values) {
            addCriterion("case_lon not in", values, "caseLon");
            return (Criteria) this;
        }

        public Criteria andCaseLonBetween(Double value1, Double value2) {
            addCriterion("case_lon between", value1, value2, "caseLon");
            return (Criteria) this;
        }

        public Criteria andCaseLonNotBetween(Double value1, Double value2) {
            addCriterion("case_lon not between", value1, value2, "caseLon");
            return (Criteria) this;
        }

        public Criteria andCaseLatIsNull() {
            addCriterion("case_lat is null");
            return (Criteria) this;
        }

        public Criteria andCaseLatIsNotNull() {
            addCriterion("case_lat is not null");
            return (Criteria) this;
        }

        public Criteria andCaseLatEqualTo(Double value) {
            addCriterion("case_lat =", value, "caseLat");
            return (Criteria) this;
        }

        public Criteria andCaseLatNotEqualTo(Double value) {
            addCriterion("case_lat <>", value, "caseLat");
            return (Criteria) this;
        }

        public Criteria andCaseLatGreaterThan(Double value) {
            addCriterion("case_lat >", value, "caseLat");
            return (Criteria) this;
        }

        public Criteria andCaseLatGreaterThanOrEqualTo(Double value) {
            addCriterion("case_lat >=", value, "caseLat");
            return (Criteria) this;
        }

        public Criteria andCaseLatLessThan(Double value) {
            addCriterion("case_lat <", value, "caseLat");
            return (Criteria) this;
        }

        public Criteria andCaseLatLessThanOrEqualTo(Double value) {
            addCriterion("case_lat <=", value, "caseLat");
            return (Criteria) this;
        }

        public Criteria andCaseLatIn(List<Double> values) {
            addCriterion("case_lat in", values, "caseLat");
            return (Criteria) this;
        }

        public Criteria andCaseLatNotIn(List<Double> values) {
            addCriterion("case_lat not in", values, "caseLat");
            return (Criteria) this;
        }

        public Criteria andCaseLatBetween(Double value1, Double value2) {
            addCriterion("case_lat between", value1, value2, "caseLat");
            return (Criteria) this;
        }

        public Criteria andCaseLatNotBetween(Double value1, Double value2) {
            addCriterion("case_lat not between", value1, value2, "caseLat");
            return (Criteria) this;
        }

        public Criteria andGeoCodeIsNull() {
            addCriterion("geo_code is null");
            return (Criteria) this;
        }

        public Criteria andGeoCodeIsNotNull() {
            addCriterion("geo_code is not null");
            return (Criteria) this;
        }

        public Criteria andGeoCodeEqualTo(String value) {
            addCriterion("geo_code =", value, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeNotEqualTo(String value) {
            addCriterion("geo_code <>", value, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeGreaterThan(String value) {
            addCriterion("geo_code >", value, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeGreaterThanOrEqualTo(String value) {
            addCriterion("geo_code >=", value, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeLessThan(String value) {
            addCriterion("geo_code <", value, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeLessThanOrEqualTo(String value) {
            addCriterion("geo_code <=", value, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeLike(String value) {
            addCriterion("geo_code like", value, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeNotLike(String value) {
            addCriterion("geo_code not like", value, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeIn(List<String> values) {
            addCriterion("geo_code in", values, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeNotIn(List<String> values) {
            addCriterion("geo_code not in", values, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeBetween(String value1, String value2) {
            addCriterion("geo_code between", value1, value2, "geoCode");
            return (Criteria) this;
        }

        public Criteria andGeoCodeNotBetween(String value1, String value2) {
            addCriterion("geo_code not between", value1, value2, "geoCode");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
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
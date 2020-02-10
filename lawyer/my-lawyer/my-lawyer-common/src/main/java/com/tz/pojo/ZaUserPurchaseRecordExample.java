package com.tz.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZaUserPurchaseRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZaUserPurchaseRecordExample() {
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

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(Integer value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(Integer value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(Integer value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(Integer value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<Integer> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<Integer> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andDealCodeIsNull() {
            addCriterion("deal_code is null");
            return (Criteria) this;
        }

        public Criteria andDealCodeIsNotNull() {
            addCriterion("deal_code is not null");
            return (Criteria) this;
        }

        public Criteria andDealCodeEqualTo(String value) {
            addCriterion("deal_code =", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeNotEqualTo(String value) {
            addCriterion("deal_code <>", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeGreaterThan(String value) {
            addCriterion("deal_code >", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeGreaterThanOrEqualTo(String value) {
            addCriterion("deal_code >=", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeLessThan(String value) {
            addCriterion("deal_code <", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeLessThanOrEqualTo(String value) {
            addCriterion("deal_code <=", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeLike(String value) {
            addCriterion("deal_code like", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeNotLike(String value) {
            addCriterion("deal_code not like", value, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeIn(List<String> values) {
            addCriterion("deal_code in", values, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeNotIn(List<String> values) {
            addCriterion("deal_code not in", values, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeBetween(String value1, String value2) {
            addCriterion("deal_code between", value1, value2, "dealCode");
            return (Criteria) this;
        }

        public Criteria andDealCodeNotBetween(String value1, String value2) {
            addCriterion("deal_code not between", value1, value2, "dealCode");
            return (Criteria) this;
        }

        public Criteria andPayCashIsNull() {
            addCriterion("pay_cash is null");
            return (Criteria) this;
        }

        public Criteria andPayCashIsNotNull() {
            addCriterion("pay_cash is not null");
            return (Criteria) this;
        }

        public Criteria andPayCashEqualTo(BigDecimal value) {
            addCriterion("pay_cash =", value, "payCash");
            return (Criteria) this;
        }

        public Criteria andPayCashNotEqualTo(BigDecimal value) {
            addCriterion("pay_cash <>", value, "payCash");
            return (Criteria) this;
        }

        public Criteria andPayCashGreaterThan(BigDecimal value) {
            addCriterion("pay_cash >", value, "payCash");
            return (Criteria) this;
        }

        public Criteria andPayCashGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_cash >=", value, "payCash");
            return (Criteria) this;
        }

        public Criteria andPayCashLessThan(BigDecimal value) {
            addCriterion("pay_cash <", value, "payCash");
            return (Criteria) this;
        }

        public Criteria andPayCashLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_cash <=", value, "payCash");
            return (Criteria) this;
        }

        public Criteria andPayCashIn(List<BigDecimal> values) {
            addCriterion("pay_cash in", values, "payCash");
            return (Criteria) this;
        }

        public Criteria andPayCashNotIn(List<BigDecimal> values) {
            addCriterion("pay_cash not in", values, "payCash");
            return (Criteria) this;
        }

        public Criteria andPayCashBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_cash between", value1, value2, "payCash");
            return (Criteria) this;
        }

        public Criteria andPayCashNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_cash not between", value1, value2, "payCash");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(Integer value) {
            addCriterion("pay_status =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(Integer value) {
            addCriterion("pay_status <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(Integer value) {
            addCriterion("pay_status >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_status >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(Integer value) {
            addCriterion("pay_status <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(Integer value) {
            addCriterion("pay_status <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<Integer> values) {
            addCriterion("pay_status in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<Integer> values) {
            addCriterion("pay_status not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(Integer value1, Integer value2) {
            addCriterion("pay_status between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_status not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andConsumTypeIsNull() {
            addCriterion("consum_type is null");
            return (Criteria) this;
        }

        public Criteria andConsumTypeIsNotNull() {
            addCriterion("consum_type is not null");
            return (Criteria) this;
        }

        public Criteria andConsumTypeEqualTo(Integer value) {
            addCriterion("consum_type =", value, "consumType");
            return (Criteria) this;
        }

        public Criteria andConsumTypeNotEqualTo(Integer value) {
            addCriterion("consum_type <>", value, "consumType");
            return (Criteria) this;
        }

        public Criteria andConsumTypeGreaterThan(Integer value) {
            addCriterion("consum_type >", value, "consumType");
            return (Criteria) this;
        }

        public Criteria andConsumTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("consum_type >=", value, "consumType");
            return (Criteria) this;
        }

        public Criteria andConsumTypeLessThan(Integer value) {
            addCriterion("consum_type <", value, "consumType");
            return (Criteria) this;
        }

        public Criteria andConsumTypeLessThanOrEqualTo(Integer value) {
            addCriterion("consum_type <=", value, "consumType");
            return (Criteria) this;
        }

        public Criteria andConsumTypeIn(List<Integer> values) {
            addCriterion("consum_type in", values, "consumType");
            return (Criteria) this;
        }

        public Criteria andConsumTypeNotIn(List<Integer> values) {
            addCriterion("consum_type not in", values, "consumType");
            return (Criteria) this;
        }

        public Criteria andConsumTypeBetween(Integer value1, Integer value2) {
            addCriterion("consum_type between", value1, value2, "consumType");
            return (Criteria) this;
        }

        public Criteria andConsumTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("consum_type not between", value1, value2, "consumType");
            return (Criteria) this;
        }

        public Criteria andInOutIsNull() {
            addCriterion("in_out is null");
            return (Criteria) this;
        }

        public Criteria andInOutIsNotNull() {
            addCriterion("in_out is not null");
            return (Criteria) this;
        }

        public Criteria andInOutEqualTo(Integer value) {
            addCriterion("in_out =", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutNotEqualTo(Integer value) {
            addCriterion("in_out <>", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutGreaterThan(Integer value) {
            addCriterion("in_out >", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("in_out >=", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutLessThan(Integer value) {
            addCriterion("in_out <", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutLessThanOrEqualTo(Integer value) {
            addCriterion("in_out <=", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutIn(List<Integer> values) {
            addCriterion("in_out in", values, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutNotIn(List<Integer> values) {
            addCriterion("in_out not in", values, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutBetween(Integer value1, Integer value2) {
            addCriterion("in_out between", value1, value2, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutNotBetween(Integer value1, Integer value2) {
            addCriterion("in_out not between", value1, value2, "inOut");
            return (Criteria) this;
        }

        public Criteria andEventDescIsNull() {
            addCriterion("event_desc is null");
            return (Criteria) this;
        }

        public Criteria andEventDescIsNotNull() {
            addCriterion("event_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEventDescEqualTo(String value) {
            addCriterion("event_desc =", value, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescNotEqualTo(String value) {
            addCriterion("event_desc <>", value, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescGreaterThan(String value) {
            addCriterion("event_desc >", value, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescGreaterThanOrEqualTo(String value) {
            addCriterion("event_desc >=", value, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescLessThan(String value) {
            addCriterion("event_desc <", value, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescLessThanOrEqualTo(String value) {
            addCriterion("event_desc <=", value, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescLike(String value) {
            addCriterion("event_desc like", value, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescNotLike(String value) {
            addCriterion("event_desc not like", value, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescIn(List<String> values) {
            addCriterion("event_desc in", values, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescNotIn(List<String> values) {
            addCriterion("event_desc not in", values, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescBetween(String value1, String value2) {
            addCriterion("event_desc between", value1, value2, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andEventDescNotBetween(String value1, String value2) {
            addCriterion("event_desc not between", value1, value2, "eventDesc");
            return (Criteria) this;
        }

        public Criteria andUserAccountIsNull() {
            addCriterion("user_account is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountIsNotNull() {
            addCriterion("user_account is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountEqualTo(String value) {
            addCriterion("user_account =", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotEqualTo(String value) {
            addCriterion("user_account <>", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountGreaterThan(String value) {
            addCriterion("user_account >", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountGreaterThanOrEqualTo(String value) {
            addCriterion("user_account >=", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountLessThan(String value) {
            addCriterion("user_account <", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountLessThanOrEqualTo(String value) {
            addCriterion("user_account <=", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountLike(String value) {
            addCriterion("user_account like", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotLike(String value) {
            addCriterion("user_account not like", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountIn(List<String> values) {
            addCriterion("user_account in", values, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotIn(List<String> values) {
            addCriterion("user_account not in", values, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountBetween(String value1, String value2) {
            addCriterion("user_account between", value1, value2, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotBetween(String value1, String value2) {
            addCriterion("user_account not between", value1, value2, "userAccount");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameIsNull() {
            addCriterion("payee_real_name is null");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameIsNotNull() {
            addCriterion("payee_real_name is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameEqualTo(String value) {
            addCriterion("payee_real_name =", value, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameNotEqualTo(String value) {
            addCriterion("payee_real_name <>", value, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameGreaterThan(String value) {
            addCriterion("payee_real_name >", value, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("payee_real_name >=", value, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameLessThan(String value) {
            addCriterion("payee_real_name <", value, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameLessThanOrEqualTo(String value) {
            addCriterion("payee_real_name <=", value, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameLike(String value) {
            addCriterion("payee_real_name like", value, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameNotLike(String value) {
            addCriterion("payee_real_name not like", value, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameIn(List<String> values) {
            addCriterion("payee_real_name in", values, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameNotIn(List<String> values) {
            addCriterion("payee_real_name not in", values, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameBetween(String value1, String value2) {
            addCriterion("payee_real_name between", value1, value2, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andPayeeRealNameNotBetween(String value1, String value2) {
            addCriterion("payee_real_name not between", value1, value2, "payeeRealName");
            return (Criteria) this;
        }

        public Criteria andConsumTimeIsNull() {
            addCriterion("consum_time is null");
            return (Criteria) this;
        }

        public Criteria andConsumTimeIsNotNull() {
            addCriterion("consum_time is not null");
            return (Criteria) this;
        }

        public Criteria andConsumTimeEqualTo(Date value) {
            addCriterion("consum_time =", value, "consumTime");
            return (Criteria) this;
        }

        public Criteria andConsumTimeNotEqualTo(Date value) {
            addCriterion("consum_time <>", value, "consumTime");
            return (Criteria) this;
        }

        public Criteria andConsumTimeGreaterThan(Date value) {
            addCriterion("consum_time >", value, "consumTime");
            return (Criteria) this;
        }

        public Criteria andConsumTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("consum_time >=", value, "consumTime");
            return (Criteria) this;
        }

        public Criteria andConsumTimeLessThan(Date value) {
            addCriterion("consum_time <", value, "consumTime");
            return (Criteria) this;
        }

        public Criteria andConsumTimeLessThanOrEqualTo(Date value) {
            addCriterion("consum_time <=", value, "consumTime");
            return (Criteria) this;
        }

        public Criteria andConsumTimeIn(List<Date> values) {
            addCriterion("consum_time in", values, "consumTime");
            return (Criteria) this;
        }

        public Criteria andConsumTimeNotIn(List<Date> values) {
            addCriterion("consum_time not in", values, "consumTime");
            return (Criteria) this;
        }

        public Criteria andConsumTimeBetween(Date value1, Date value2) {
            addCriterion("consum_time between", value1, value2, "consumTime");
            return (Criteria) this;
        }

        public Criteria andConsumTimeNotBetween(Date value1, Date value2) {
            addCriterion("consum_time not between", value1, value2, "consumTime");
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
package com.tz.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZaUserProfitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZaUserProfitExample() {
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

        public Criteria andCoinConsumTotalIsNull() {
            addCriterion("coin_consum_total is null");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalIsNotNull() {
            addCriterion("coin_consum_total is not null");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalEqualTo(BigDecimal value) {
            addCriterion("coin_consum_total =", value, "coinConsumTotal");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalNotEqualTo(BigDecimal value) {
            addCriterion("coin_consum_total <>", value, "coinConsumTotal");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalGreaterThan(BigDecimal value) {
            addCriterion("coin_consum_total >", value, "coinConsumTotal");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_consum_total >=", value, "coinConsumTotal");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalLessThan(BigDecimal value) {
            addCriterion("coin_consum_total <", value, "coinConsumTotal");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_consum_total <=", value, "coinConsumTotal");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalIn(List<BigDecimal> values) {
            addCriterion("coin_consum_total in", values, "coinConsumTotal");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalNotIn(List<BigDecimal> values) {
            addCriterion("coin_consum_total not in", values, "coinConsumTotal");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_consum_total between", value1, value2, "coinConsumTotal");
            return (Criteria) this;
        }

        public Criteria andCoinConsumTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_consum_total not between", value1, value2, "coinConsumTotal");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusIsNull() {
            addCriterion("coin_surplus is null");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusIsNotNull() {
            addCriterion("coin_surplus is not null");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusEqualTo(BigDecimal value) {
            addCriterion("coin_surplus =", value, "coinSurplus");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusNotEqualTo(BigDecimal value) {
            addCriterion("coin_surplus <>", value, "coinSurplus");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusGreaterThan(BigDecimal value) {
            addCriterion("coin_surplus >", value, "coinSurplus");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_surplus >=", value, "coinSurplus");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusLessThan(BigDecimal value) {
            addCriterion("coin_surplus <", value, "coinSurplus");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_surplus <=", value, "coinSurplus");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusIn(List<BigDecimal> values) {
            addCriterion("coin_surplus in", values, "coinSurplus");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusNotIn(List<BigDecimal> values) {
            addCriterion("coin_surplus not in", values, "coinSurplus");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_surplus between", value1, value2, "coinSurplus");
            return (Criteria) this;
        }

        public Criteria andCoinSurplusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_surplus not between", value1, value2, "coinSurplus");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeIsNull() {
            addCriterion("coin_income is null");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeIsNotNull() {
            addCriterion("coin_income is not null");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeEqualTo(BigDecimal value) {
            addCriterion("coin_income =", value, "coinIncome");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeNotEqualTo(BigDecimal value) {
            addCriterion("coin_income <>", value, "coinIncome");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeGreaterThan(BigDecimal value) {
            addCriterion("coin_income >", value, "coinIncome");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_income >=", value, "coinIncome");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeLessThan(BigDecimal value) {
            addCriterion("coin_income <", value, "coinIncome");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_income <=", value, "coinIncome");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeIn(List<BigDecimal> values) {
            addCriterion("coin_income in", values, "coinIncome");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeNotIn(List<BigDecimal> values) {
            addCriterion("coin_income not in", values, "coinIncome");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_income between", value1, value2, "coinIncome");
            return (Criteria) this;
        }

        public Criteria andCoinIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_income not between", value1, value2, "coinIncome");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalIsNull() {
            addCriterion("coin_in_total is null");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalIsNotNull() {
            addCriterion("coin_in_total is not null");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalEqualTo(BigDecimal value) {
            addCriterion("coin_in_total =", value, "coinInTotal");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalNotEqualTo(BigDecimal value) {
            addCriterion("coin_in_total <>", value, "coinInTotal");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalGreaterThan(BigDecimal value) {
            addCriterion("coin_in_total >", value, "coinInTotal");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_in_total >=", value, "coinInTotal");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalLessThan(BigDecimal value) {
            addCriterion("coin_in_total <", value, "coinInTotal");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_in_total <=", value, "coinInTotal");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalIn(List<BigDecimal> values) {
            addCriterion("coin_in_total in", values, "coinInTotal");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalNotIn(List<BigDecimal> values) {
            addCriterion("coin_in_total not in", values, "coinInTotal");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_in_total between", value1, value2, "coinInTotal");
            return (Criteria) this;
        }

        public Criteria andCoinInTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_in_total not between", value1, value2, "coinInTotal");
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

        public Criteria andObTypeIsNull() {
            addCriterion("ob_type is null");
            return (Criteria) this;
        }

        public Criteria andObTypeIsNotNull() {
            addCriterion("ob_type is not null");
            return (Criteria) this;
        }

        public Criteria andObTypeEqualTo(Integer value) {
            addCriterion("ob_type =", value, "obType");
            return (Criteria) this;
        }

        public Criteria andObTypeNotEqualTo(Integer value) {
            addCriterion("ob_type <>", value, "obType");
            return (Criteria) this;
        }

        public Criteria andObTypeGreaterThan(Integer value) {
            addCriterion("ob_type >", value, "obType");
            return (Criteria) this;
        }

        public Criteria andObTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ob_type >=", value, "obType");
            return (Criteria) this;
        }

        public Criteria andObTypeLessThan(Integer value) {
            addCriterion("ob_type <", value, "obType");
            return (Criteria) this;
        }

        public Criteria andObTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ob_type <=", value, "obType");
            return (Criteria) this;
        }

        public Criteria andObTypeIn(List<Integer> values) {
            addCriterion("ob_type in", values, "obType");
            return (Criteria) this;
        }

        public Criteria andObTypeNotIn(List<Integer> values) {
            addCriterion("ob_type not in", values, "obType");
            return (Criteria) this;
        }

        public Criteria andObTypeBetween(Integer value1, Integer value2) {
            addCriterion("ob_type between", value1, value2, "obType");
            return (Criteria) this;
        }

        public Criteria andObTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ob_type not between", value1, value2, "obType");
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

        public Criteria andLastUseTimeIsNull() {
            addCriterion("last_use_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeIsNotNull() {
            addCriterion("last_use_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeEqualTo(Date value) {
            addCriterion("last_use_time =", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeNotEqualTo(Date value) {
            addCriterion("last_use_time <>", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeGreaterThan(Date value) {
            addCriterion("last_use_time >", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_use_time >=", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeLessThan(Date value) {
            addCriterion("last_use_time <", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_use_time <=", value, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeIn(List<Date> values) {
            addCriterion("last_use_time in", values, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeNotIn(List<Date> values) {
            addCriterion("last_use_time not in", values, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeBetween(Date value1, Date value2) {
            addCriterion("last_use_time between", value1, value2, "lastUseTime");
            return (Criteria) this;
        }

        public Criteria andLastUseTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_use_time not between", value1, value2, "lastUseTime");
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
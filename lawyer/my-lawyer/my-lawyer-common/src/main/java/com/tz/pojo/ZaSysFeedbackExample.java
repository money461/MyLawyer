package com.tz.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZaSysFeedbackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZaSysFeedbackExample() {
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

        public Criteria andFeedbackInfoIsNull() {
            addCriterion("feedback_info is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoIsNotNull() {
            addCriterion("feedback_info is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoEqualTo(String value) {
            addCriterion("feedback_info =", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotEqualTo(String value) {
            addCriterion("feedback_info <>", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoGreaterThan(String value) {
            addCriterion("feedback_info >", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_info >=", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoLessThan(String value) {
            addCriterion("feedback_info <", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoLessThanOrEqualTo(String value) {
            addCriterion("feedback_info <=", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoLike(String value) {
            addCriterion("feedback_info like", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotLike(String value) {
            addCriterion("feedback_info not like", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoIn(List<String> values) {
            addCriterion("feedback_info in", values, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotIn(List<String> values) {
            addCriterion("feedback_info not in", values, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoBetween(String value1, String value2) {
            addCriterion("feedback_info between", value1, value2, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotBetween(String value1, String value2) {
            addCriterion("feedback_info not between", value1, value2, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusIsNull() {
            addCriterion("feedback_status is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusIsNotNull() {
            addCriterion("feedback_status is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusEqualTo(Integer value) {
            addCriterion("feedback_status =", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusNotEqualTo(Integer value) {
            addCriterion("feedback_status <>", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusGreaterThan(Integer value) {
            addCriterion("feedback_status >", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("feedback_status >=", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusLessThan(Integer value) {
            addCriterion("feedback_status <", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusLessThanOrEqualTo(Integer value) {
            addCriterion("feedback_status <=", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusIn(List<Integer> values) {
            addCriterion("feedback_status in", values, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusNotIn(List<Integer> values) {
            addCriterion("feedback_status not in", values, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusBetween(Integer value1, Integer value2) {
            addCriterion("feedback_status between", value1, value2, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("feedback_status not between", value1, value2, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeIsNull() {
            addCriterion("feedback_time is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeIsNotNull() {
            addCriterion("feedback_time is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeEqualTo(Date value) {
            addCriterion("feedback_time =", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeNotEqualTo(Date value) {
            addCriterion("feedback_time <>", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeGreaterThan(Date value) {
            addCriterion("feedback_time >", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("feedback_time >=", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeLessThan(Date value) {
            addCriterion("feedback_time <", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeLessThanOrEqualTo(Date value) {
            addCriterion("feedback_time <=", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeIn(List<Date> values) {
            addCriterion("feedback_time in", values, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeNotIn(List<Date> values) {
            addCriterion("feedback_time not in", values, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeBetween(Date value1, Date value2) {
            addCriterion("feedback_time between", value1, value2, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeNotBetween(Date value1, Date value2) {
            addCriterion("feedback_time not between", value1, value2, "feedbackTime");
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

        public Criteria andRevoveryTimeIsNull() {
            addCriterion("revovery_time is null");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeIsNotNull() {
            addCriterion("revovery_time is not null");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeEqualTo(Date value) {
            addCriterion("revovery_time =", value, "revoveryTime");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeNotEqualTo(Date value) {
            addCriterion("revovery_time <>", value, "revoveryTime");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeGreaterThan(Date value) {
            addCriterion("revovery_time >", value, "revoveryTime");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("revovery_time >=", value, "revoveryTime");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeLessThan(Date value) {
            addCriterion("revovery_time <", value, "revoveryTime");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("revovery_time <=", value, "revoveryTime");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeIn(List<Date> values) {
            addCriterion("revovery_time in", values, "revoveryTime");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeNotIn(List<Date> values) {
            addCriterion("revovery_time not in", values, "revoveryTime");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeBetween(Date value1, Date value2) {
            addCriterion("revovery_time between", value1, value2, "revoveryTime");
            return (Criteria) this;
        }

        public Criteria andRevoveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("revovery_time not between", value1, value2, "revoveryTime");
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
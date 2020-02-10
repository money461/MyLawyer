package com.tz.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZaUserChatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZaUserChatExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
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

        public Criteria andSessionCodeIsNull() {
            addCriterion("session_code is null");
            return (Criteria) this;
        }

        public Criteria andSessionCodeIsNotNull() {
            addCriterion("session_code is not null");
            return (Criteria) this;
        }

        public Criteria andSessionCodeEqualTo(String value) {
            addCriterion("session_code =", value, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeNotEqualTo(String value) {
            addCriterion("session_code <>", value, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeGreaterThan(String value) {
            addCriterion("session_code >", value, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("session_code >=", value, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeLessThan(String value) {
            addCriterion("session_code <", value, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeLessThanOrEqualTo(String value) {
            addCriterion("session_code <=", value, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeLike(String value) {
            addCriterion("session_code like", value, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeNotLike(String value) {
            addCriterion("session_code not like", value, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeIn(List<String> values) {
            addCriterion("session_code in", values, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeNotIn(List<String> values) {
            addCriterion("session_code not in", values, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeBetween(String value1, String value2) {
            addCriterion("session_code between", value1, value2, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andSessionCodeNotBetween(String value1, String value2) {
            addCriterion("session_code not between", value1, value2, "sessionCode");
            return (Criteria) this;
        }

        public Criteria andChatCategoryIsNull() {
            addCriterion("chat_category is null");
            return (Criteria) this;
        }

        public Criteria andChatCategoryIsNotNull() {
            addCriterion("chat_category is not null");
            return (Criteria) this;
        }

        public Criteria andChatCategoryEqualTo(Integer value) {
            addCriterion("chat_category =", value, "chatCategory");
            return (Criteria) this;
        }

        public Criteria andChatCategoryNotEqualTo(Integer value) {
            addCriterion("chat_category <>", value, "chatCategory");
            return (Criteria) this;
        }

        public Criteria andChatCategoryGreaterThan(Integer value) {
            addCriterion("chat_category >", value, "chatCategory");
            return (Criteria) this;
        }

        public Criteria andChatCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("chat_category >=", value, "chatCategory");
            return (Criteria) this;
        }

        public Criteria andChatCategoryLessThan(Integer value) {
            addCriterion("chat_category <", value, "chatCategory");
            return (Criteria) this;
        }

        public Criteria andChatCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("chat_category <=", value, "chatCategory");
            return (Criteria) this;
        }

        public Criteria andChatCategoryIn(List<Integer> values) {
            addCriterion("chat_category in", values, "chatCategory");
            return (Criteria) this;
        }

        public Criteria andChatCategoryNotIn(List<Integer> values) {
            addCriterion("chat_category not in", values, "chatCategory");
            return (Criteria) this;
        }

        public Criteria andChatCategoryBetween(Integer value1, Integer value2) {
            addCriterion("chat_category between", value1, value2, "chatCategory");
            return (Criteria) this;
        }

        public Criteria andChatCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("chat_category not between", value1, value2, "chatCategory");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
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

        public Criteria andShowStateIsNull() {
            addCriterion("show_state is null");
            return (Criteria) this;
        }

        public Criteria andShowStateIsNotNull() {
            addCriterion("show_state is not null");
            return (Criteria) this;
        }

        public Criteria andShowStateEqualTo(Integer value) {
            addCriterion("show_state =", value, "showState");
            return (Criteria) this;
        }

        public Criteria andShowStateNotEqualTo(Integer value) {
            addCriterion("show_state <>", value, "showState");
            return (Criteria) this;
        }

        public Criteria andShowStateGreaterThan(Integer value) {
            addCriterion("show_state >", value, "showState");
            return (Criteria) this;
        }

        public Criteria andShowStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_state >=", value, "showState");
            return (Criteria) this;
        }

        public Criteria andShowStateLessThan(Integer value) {
            addCriterion("show_state <", value, "showState");
            return (Criteria) this;
        }

        public Criteria andShowStateLessThanOrEqualTo(Integer value) {
            addCriterion("show_state <=", value, "showState");
            return (Criteria) this;
        }

        public Criteria andShowStateIn(List<Integer> values) {
            addCriterion("show_state in", values, "showState");
            return (Criteria) this;
        }

        public Criteria andShowStateNotIn(List<Integer> values) {
            addCriterion("show_state not in", values, "showState");
            return (Criteria) this;
        }

        public Criteria andShowStateBetween(Integer value1, Integer value2) {
            addCriterion("show_state between", value1, value2, "showState");
            return (Criteria) this;
        }

        public Criteria andShowStateNotBetween(Integer value1, Integer value2) {
            addCriterion("show_state not between", value1, value2, "showState");
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
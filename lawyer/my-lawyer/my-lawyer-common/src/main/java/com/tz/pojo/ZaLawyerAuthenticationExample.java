package com.tz.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZaLawyerAuthenticationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZaLawyerAuthenticationExample() {
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

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(String value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(String value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(String value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(String value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(String value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(String value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(String value) {
            addCriterion("grade like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(String value) {
            addCriterion("grade not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<String> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<String> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(String value1, String value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(String value1, String value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andLawCaseIsNull() {
            addCriterion("law_case is null");
            return (Criteria) this;
        }

        public Criteria andLawCaseIsNotNull() {
            addCriterion("law_case is not null");
            return (Criteria) this;
        }

        public Criteria andLawCaseEqualTo(String value) {
            addCriterion("law_case =", value, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseNotEqualTo(String value) {
            addCriterion("law_case <>", value, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseGreaterThan(String value) {
            addCriterion("law_case >", value, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseGreaterThanOrEqualTo(String value) {
            addCriterion("law_case >=", value, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseLessThan(String value) {
            addCriterion("law_case <", value, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseLessThanOrEqualTo(String value) {
            addCriterion("law_case <=", value, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseLike(String value) {
            addCriterion("law_case like", value, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseNotLike(String value) {
            addCriterion("law_case not like", value, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseIn(List<String> values) {
            addCriterion("law_case in", values, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseNotIn(List<String> values) {
            addCriterion("law_case not in", values, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseBetween(String value1, String value2) {
            addCriterion("law_case between", value1, value2, "lawCase");
            return (Criteria) this;
        }

        public Criteria andLawCaseNotBetween(String value1, String value2) {
            addCriterion("law_case not between", value1, value2, "lawCase");
            return (Criteria) this;
        }

        public Criteria andAgeLimitIsNull() {
            addCriterion("age_limit is null");
            return (Criteria) this;
        }

        public Criteria andAgeLimitIsNotNull() {
            addCriterion("age_limit is not null");
            return (Criteria) this;
        }

        public Criteria andAgeLimitEqualTo(Integer value) {
            addCriterion("age_limit =", value, "ageLimit");
            return (Criteria) this;
        }

        public Criteria andAgeLimitNotEqualTo(Integer value) {
            addCriterion("age_limit <>", value, "ageLimit");
            return (Criteria) this;
        }

        public Criteria andAgeLimitGreaterThan(Integer value) {
            addCriterion("age_limit >", value, "ageLimit");
            return (Criteria) this;
        }

        public Criteria andAgeLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("age_limit >=", value, "ageLimit");
            return (Criteria) this;
        }

        public Criteria andAgeLimitLessThan(Integer value) {
            addCriterion("age_limit <", value, "ageLimit");
            return (Criteria) this;
        }

        public Criteria andAgeLimitLessThanOrEqualTo(Integer value) {
            addCriterion("age_limit <=", value, "ageLimit");
            return (Criteria) this;
        }

        public Criteria andAgeLimitIn(List<Integer> values) {
            addCriterion("age_limit in", values, "ageLimit");
            return (Criteria) this;
        }

        public Criteria andAgeLimitNotIn(List<Integer> values) {
            addCriterion("age_limit not in", values, "ageLimit");
            return (Criteria) this;
        }

        public Criteria andAgeLimitBetween(Integer value1, Integer value2) {
            addCriterion("age_limit between", value1, value2, "ageLimit");
            return (Criteria) this;
        }

        public Criteria andAgeLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("age_limit not between", value1, value2, "ageLimit");
            return (Criteria) this;
        }

        public Criteria andLawOfficeIsNull() {
            addCriterion("law_office is null");
            return (Criteria) this;
        }

        public Criteria andLawOfficeIsNotNull() {
            addCriterion("law_office is not null");
            return (Criteria) this;
        }

        public Criteria andLawOfficeEqualTo(String value) {
            addCriterion("law_office =", value, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeNotEqualTo(String value) {
            addCriterion("law_office <>", value, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeGreaterThan(String value) {
            addCriterion("law_office >", value, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeGreaterThanOrEqualTo(String value) {
            addCriterion("law_office >=", value, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeLessThan(String value) {
            addCriterion("law_office <", value, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeLessThanOrEqualTo(String value) {
            addCriterion("law_office <=", value, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeLike(String value) {
            addCriterion("law_office like", value, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeNotLike(String value) {
            addCriterion("law_office not like", value, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeIn(List<String> values) {
            addCriterion("law_office in", values, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeNotIn(List<String> values) {
            addCriterion("law_office not in", values, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeBetween(String value1, String value2) {
            addCriterion("law_office between", value1, value2, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawOfficeNotBetween(String value1, String value2) {
            addCriterion("law_office not between", value1, value2, "lawOffice");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceIsNull() {
            addCriterion("law_introduce is null");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceIsNotNull() {
            addCriterion("law_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceEqualTo(String value) {
            addCriterion("law_introduce =", value, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceNotEqualTo(String value) {
            addCriterion("law_introduce <>", value, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceGreaterThan(String value) {
            addCriterion("law_introduce >", value, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("law_introduce >=", value, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceLessThan(String value) {
            addCriterion("law_introduce <", value, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceLessThanOrEqualTo(String value) {
            addCriterion("law_introduce <=", value, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceLike(String value) {
            addCriterion("law_introduce like", value, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceNotLike(String value) {
            addCriterion("law_introduce not like", value, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceIn(List<String> values) {
            addCriterion("law_introduce in", values, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceNotIn(List<String> values) {
            addCriterion("law_introduce not in", values, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceBetween(String value1, String value2) {
            addCriterion("law_introduce between", value1, value2, "lawIntroduce");
            return (Criteria) this;
        }

        public Criteria andLawIntroduceNotBetween(String value1, String value2) {
            addCriterion("law_introduce not between", value1, value2, "lawIntroduce");
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

        public Criteria andLawLogoIsNull() {
            addCriterion("law_logo is null");
            return (Criteria) this;
        }

        public Criteria andLawLogoIsNotNull() {
            addCriterion("law_logo is not null");
            return (Criteria) this;
        }

        public Criteria andLawLogoEqualTo(String value) {
            addCriterion("law_logo =", value, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoNotEqualTo(String value) {
            addCriterion("law_logo <>", value, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoGreaterThan(String value) {
            addCriterion("law_logo >", value, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoGreaterThanOrEqualTo(String value) {
            addCriterion("law_logo >=", value, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoLessThan(String value) {
            addCriterion("law_logo <", value, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoLessThanOrEqualTo(String value) {
            addCriterion("law_logo <=", value, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoLike(String value) {
            addCriterion("law_logo like", value, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoNotLike(String value) {
            addCriterion("law_logo not like", value, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoIn(List<String> values) {
            addCriterion("law_logo in", values, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoNotIn(List<String> values) {
            addCriterion("law_logo not in", values, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoBetween(String value1, String value2) {
            addCriterion("law_logo between", value1, value2, "lawLogo");
            return (Criteria) this;
        }

        public Criteria andLawLogoNotBetween(String value1, String value2) {
            addCriterion("law_logo not between", value1, value2, "lawLogo");
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

        public Criteria andLawPhoneIsNull() {
            addCriterion("law_phone is null");
            return (Criteria) this;
        }

        public Criteria andLawPhoneIsNotNull() {
            addCriterion("law_phone is not null");
            return (Criteria) this;
        }

        public Criteria andLawPhoneEqualTo(String value) {
            addCriterion("law_phone =", value, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneNotEqualTo(String value) {
            addCriterion("law_phone <>", value, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneGreaterThan(String value) {
            addCriterion("law_phone >", value, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("law_phone >=", value, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneLessThan(String value) {
            addCriterion("law_phone <", value, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneLessThanOrEqualTo(String value) {
            addCriterion("law_phone <=", value, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneLike(String value) {
            addCriterion("law_phone like", value, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneNotLike(String value) {
            addCriterion("law_phone not like", value, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneIn(List<String> values) {
            addCriterion("law_phone in", values, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneNotIn(List<String> values) {
            addCriterion("law_phone not in", values, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneBetween(String value1, String value2) {
            addCriterion("law_phone between", value1, value2, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawPhoneNotBetween(String value1, String value2) {
            addCriterion("law_phone not between", value1, value2, "lawPhone");
            return (Criteria) this;
        }

        public Criteria andLawAddressIsNull() {
            addCriterion("law_address is null");
            return (Criteria) this;
        }

        public Criteria andLawAddressIsNotNull() {
            addCriterion("law_address is not null");
            return (Criteria) this;
        }

        public Criteria andLawAddressEqualTo(String value) {
            addCriterion("law_address =", value, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressNotEqualTo(String value) {
            addCriterion("law_address <>", value, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressGreaterThan(String value) {
            addCriterion("law_address >", value, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressGreaterThanOrEqualTo(String value) {
            addCriterion("law_address >=", value, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressLessThan(String value) {
            addCriterion("law_address <", value, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressLessThanOrEqualTo(String value) {
            addCriterion("law_address <=", value, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressLike(String value) {
            addCriterion("law_address like", value, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressNotLike(String value) {
            addCriterion("law_address not like", value, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressIn(List<String> values) {
            addCriterion("law_address in", values, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressNotIn(List<String> values) {
            addCriterion("law_address not in", values, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressBetween(String value1, String value2) {
            addCriterion("law_address between", value1, value2, "lawAddress");
            return (Criteria) this;
        }

        public Criteria andLawAddressNotBetween(String value1, String value2) {
            addCriterion("law_address not between", value1, value2, "lawAddress");
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

        public Criteria andLawStatusIsNull() {
            addCriterion("law_status is null");
            return (Criteria) this;
        }

        public Criteria andLawStatusIsNotNull() {
            addCriterion("law_status is not null");
            return (Criteria) this;
        }

        public Criteria andLawStatusEqualTo(Integer value) {
            addCriterion("law_status =", value, "lawStatus");
            return (Criteria) this;
        }

        public Criteria andLawStatusNotEqualTo(Integer value) {
            addCriterion("law_status <>", value, "lawStatus");
            return (Criteria) this;
        }

        public Criteria andLawStatusGreaterThan(Integer value) {
            addCriterion("law_status >", value, "lawStatus");
            return (Criteria) this;
        }

        public Criteria andLawStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("law_status >=", value, "lawStatus");
            return (Criteria) this;
        }

        public Criteria andLawStatusLessThan(Integer value) {
            addCriterion("law_status <", value, "lawStatus");
            return (Criteria) this;
        }

        public Criteria andLawStatusLessThanOrEqualTo(Integer value) {
            addCriterion("law_status <=", value, "lawStatus");
            return (Criteria) this;
        }

        public Criteria andLawStatusIn(List<Integer> values) {
            addCriterion("law_status in", values, "lawStatus");
            return (Criteria) this;
        }

        public Criteria andLawStatusNotIn(List<Integer> values) {
            addCriterion("law_status not in", values, "lawStatus");
            return (Criteria) this;
        }

        public Criteria andLawStatusBetween(Integer value1, Integer value2) {
            addCriterion("law_status between", value1, value2, "lawStatus");
            return (Criteria) this;
        }

        public Criteria andLawStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("law_status not between", value1, value2, "lawStatus");
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
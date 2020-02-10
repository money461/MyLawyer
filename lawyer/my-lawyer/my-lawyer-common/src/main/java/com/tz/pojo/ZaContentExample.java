package com.tz.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZaContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZaContentExample() {
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

        public Criteria andContentCategoryIdIsNull() {
            addCriterion("content_category_id is null");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdIsNotNull() {
            addCriterion("content_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdEqualTo(Integer value) {
            addCriterion("content_category_id =", value, "contentCategoryId");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdNotEqualTo(Integer value) {
            addCriterion("content_category_id <>", value, "contentCategoryId");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdGreaterThan(Integer value) {
            addCriterion("content_category_id >", value, "contentCategoryId");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("content_category_id >=", value, "contentCategoryId");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdLessThan(Integer value) {
            addCriterion("content_category_id <", value, "contentCategoryId");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("content_category_id <=", value, "contentCategoryId");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdIn(List<Integer> values) {
            addCriterion("content_category_id in", values, "contentCategoryId");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdNotIn(List<Integer> values) {
            addCriterion("content_category_id not in", values, "contentCategoryId");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("content_category_id between", value1, value2, "contentCategoryId");
            return (Criteria) this;
        }

        public Criteria andContentCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("content_category_id not between", value1, value2, "contentCategoryId");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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

        public Criteria andContentTitleIsNull() {
            addCriterion("content_title is null");
            return (Criteria) this;
        }

        public Criteria andContentTitleIsNotNull() {
            addCriterion("content_title is not null");
            return (Criteria) this;
        }

        public Criteria andContentTitleEqualTo(String value) {
            addCriterion("content_title =", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleNotEqualTo(String value) {
            addCriterion("content_title <>", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleGreaterThan(String value) {
            addCriterion("content_title >", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleGreaterThanOrEqualTo(String value) {
            addCriterion("content_title >=", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleLessThan(String value) {
            addCriterion("content_title <", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleLessThanOrEqualTo(String value) {
            addCriterion("content_title <=", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleLike(String value) {
            addCriterion("content_title like", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleNotLike(String value) {
            addCriterion("content_title not like", value, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleIn(List<String> values) {
            addCriterion("content_title in", values, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleNotIn(List<String> values) {
            addCriterion("content_title not in", values, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleBetween(String value1, String value2) {
            addCriterion("content_title between", value1, value2, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andContentTitleNotBetween(String value1, String value2) {
            addCriterion("content_title not between", value1, value2, "contentTitle");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNull() {
            addCriterion("link_url is null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNotNull() {
            addCriterion("link_url is not null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlEqualTo(String value) {
            addCriterion("link_url =", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotEqualTo(String value) {
            addCriterion("link_url <>", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThan(String value) {
            addCriterion("link_url >", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("link_url >=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThan(String value) {
            addCriterion("link_url <", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThanOrEqualTo(String value) {
            addCriterion("link_url <=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLike(String value) {
            addCriterion("link_url like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotLike(String value) {
            addCriterion("link_url not like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIn(List<String> values) {
            addCriterion("link_url in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotIn(List<String> values) {
            addCriterion("link_url not in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlBetween(String value1, String value2) {
            addCriterion("link_url between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotBetween(String value1, String value2) {
            addCriterion("link_url not between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlIsNull() {
            addCriterion("homepage_url is null");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlIsNotNull() {
            addCriterion("homepage_url is not null");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlEqualTo(String value) {
            addCriterion("homepage_url =", value, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlNotEqualTo(String value) {
            addCriterion("homepage_url <>", value, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlGreaterThan(String value) {
            addCriterion("homepage_url >", value, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("homepage_url >=", value, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlLessThan(String value) {
            addCriterion("homepage_url <", value, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlLessThanOrEqualTo(String value) {
            addCriterion("homepage_url <=", value, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlLike(String value) {
            addCriterion("homepage_url like", value, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlNotLike(String value) {
            addCriterion("homepage_url not like", value, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlIn(List<String> values) {
            addCriterion("homepage_url in", values, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlNotIn(List<String> values) {
            addCriterion("homepage_url not in", values, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlBetween(String value1, String value2) {
            addCriterion("homepage_url between", value1, value2, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andHomepageUrlNotBetween(String value1, String value2) {
            addCriterion("homepage_url not between", value1, value2, "homepageUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlIsNull() {
            addCriterion("pricture_url is null");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlIsNotNull() {
            addCriterion("pricture_url is not null");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlEqualTo(String value) {
            addCriterion("pricture_url =", value, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlNotEqualTo(String value) {
            addCriterion("pricture_url <>", value, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlGreaterThan(String value) {
            addCriterion("pricture_url >", value, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pricture_url >=", value, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlLessThan(String value) {
            addCriterion("pricture_url <", value, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlLessThanOrEqualTo(String value) {
            addCriterion("pricture_url <=", value, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlLike(String value) {
            addCriterion("pricture_url like", value, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlNotLike(String value) {
            addCriterion("pricture_url not like", value, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlIn(List<String> values) {
            addCriterion("pricture_url in", values, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlNotIn(List<String> values) {
            addCriterion("pricture_url not in", values, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlBetween(String value1, String value2) {
            addCriterion("pricture_url between", value1, value2, "prictureUrl");
            return (Criteria) this;
        }

        public Criteria andPrictureUrlNotBetween(String value1, String value2) {
            addCriterion("pricture_url not between", value1, value2, "prictureUrl");
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
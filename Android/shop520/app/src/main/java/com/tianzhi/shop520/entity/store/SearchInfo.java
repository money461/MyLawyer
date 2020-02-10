package com.tianzhi.shop520.entity.store;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/11/15.
 */

public class SearchInfo implements Serializable{
    public String pageNum;
    public String pageSize;
    public int size;
    public String startRow;
    public String endRow;
    public String total;
    public String pages;
    public ArrayList<GoodsInfoEntity> list;
    public String prePage;
    public String nextPage;
    public String isFirstPage;
    public String isLastPage;
    public String hasPreviousPage;
    public String hasNextPage;
    public String navigatePages;
    public ArrayList<String> navigatepageNums;
    public String navigateFirstPage;
    public String navigateLastPage;
    public String firstPage;
    public String lastPage;

    @Override
    public String toString() {
        return "SearchInfo{" +
                "pageNum='" + pageNum + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", size=" + size +
                ", startRow='" + startRow + '\'' +
                ", endRow='" + endRow + '\'' +
                ", total='" + total + '\'' +
                ", pages='" + pages + '\'' +
                ", list=" + list +
                ", prePage='" + prePage + '\'' +
                ", nextPage='" + nextPage + '\'' +
                ", isFirstPage='" + isFirstPage + '\'' +
                ", isLastPage='" + isLastPage + '\'' +
                ", hasPreviousPage='" + hasPreviousPage + '\'' +
                ", hasNextPage='" + hasNextPage + '\'' +
                ", navigatePages='" + navigatePages + '\'' +
                ", navigatepageNums=" + navigatepageNums +
                ", navigateFirstPage='" + navigateFirstPage + '\'' +
                ", navigateLastPage='" + navigateLastPage + '\'' +
                ", firstPage='" + firstPage + '\'' +
                ", lastPage='" + lastPage + '\'' +
                '}';
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStartRow() {
        return startRow;
    }

    public void setStartRow(String startRow) {
        this.startRow = startRow;
    }

    public String getEndRow() {
        return endRow;
    }

    public void setEndRow(String endRow) {
        this.endRow = endRow;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ArrayList<GoodsInfoEntity> getList() {
        return list;
    }

    public void setList(ArrayList<GoodsInfoEntity> list) {
        this.list = list;
    }

    public String getPrePage() {
        return prePage;
    }

    public void setPrePage(String prePage) {
        this.prePage = prePage;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(String isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public String getIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(String isLastPage) {
        this.isLastPage = isLastPage;
    }

    public String getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(String hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public String getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(String hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public String getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(String navigatePages) {
        this.navigatePages = navigatePages;
    }

    public ArrayList<String> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(ArrayList<String> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public String getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(String navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public String getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(String navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public String getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
    }

    public String getLastPage() {
        return lastPage;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }
}

/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 07-06-2023      1.0                 DucTM           First Implement
 * 13-06-2023      1.0                 DucTM           Update paging from database
 */
package utils;

public class Pagination {

    private int totalItems;
    private int itemsPerPage;
    private int index;
    private int totalPage;
    private int offset;
    private int pageStart;
    private int pageEnd;

    public Pagination() {
    }

    /**
     *
     * @param totalItems the total number of items in the web
     * @param itemsPerPage the number of items displayed in one page
     * @param index the current page index
     */
    public Pagination(int totalItems, int itemsPerPage, int index) {
        this.totalItems = totalItems < 0 ? 0 : totalItems;
        this.itemsPerPage = itemsPerPage < 1 ? 1 : itemsPerPage;
        totalPage = (totalItems + itemsPerPage - 1) / itemsPerPage;
        this.index = index < 1 ? 1 : index > totalPage ? totalPage : index;
        this.offset = this.itemsPerPage * (this.index - 1);
        pageStart = this.index - 1;
        if (index < 3 || totalPage == 4) {
            pageStart = 2;
        }
        if (totalPage > 4 && index >= totalPage - 1) {
            pageStart = totalPage - 3;
        }
        pageEnd = pageStart + 2 > totalPage - 1 ? totalPage - 1 : pageStart + 2;
    }

    public int getPageStart() {
        return pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public int getIndex() {
        return index;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

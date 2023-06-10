/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 07-06-2023      1.0                 DucTM           First Implement
 */
package commonutils;

public class Paging {

    private int totalItems;
    private int itemsPerPage;
    private int index;
    private int totalPage;
    private int begin;
    private int end;
    private int pageStart;
    private int pageEnd;

    public Paging() {
    }

    /**
     *
     * @param totalItems the total number of items in the web
     * @param itemsPerPage the number of items displayed in one page
     * @param index the current page index
     */
    public Paging(int totalItems, int itemsPerPage, int index) {
        this.totalItems = totalItems < 0 ? 0 : totalItems;
        this.itemsPerPage = itemsPerPage < 1 ? 1 : itemsPerPage;
        this.index = index < 0 ? 0 : index;
    }

    /**
     * Calculate the total number of page, the begin and end index in the items
     * collections for the items in current page, and the smallest and biggest
     * page number appear on the screen
     */
    public void calculate() {
        if (totalItems == 0) {
            index = totalPage = begin = end = pageStart = pageEnd = 0;
            return;
        }
        totalPage = (totalItems + itemsPerPage - 1) / itemsPerPage;
        begin = index * itemsPerPage;
        end = begin + itemsPerPage - 1 > totalItems ? totalItems - 1 : begin + itemsPerPage - 1; //prevent out of bound exception
        pageStart = index - 1;
        if (index < 2 || totalPage == 4) {
            pageStart = 1;
        }
        if (totalPage > 4 && index >= totalPage - 2) {
            pageStart = totalPage - 4;
        }
        pageEnd = pageStart + 2 > totalPage - 2 ? totalPage - 2 : pageStart + 2;
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

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
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

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public void setEnd(int end) {
        this.end = end;
    }

}

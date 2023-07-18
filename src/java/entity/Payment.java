/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 16-07-2023      1.0                 DucTM           First Implement
 */
package entity;

public class Payment {
    private int id;
    private int agentId;
    private String bank;
    private String code;
    private String qr;

    public Payment(int agentId, String bank, String code, String qr) {
        this.agentId = agentId;
        this.bank = bank;
        this.code = code;
        this.qr = qr;
    }

    public Payment(int id, int agentId, String bank, String code, String qr) {
        this.id = id;
        this.agentId = agentId;
        this.bank = bank;
        this.code = code;
        this.qr = qr;
    }

    public Payment(int id) {
        this.id=id;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}

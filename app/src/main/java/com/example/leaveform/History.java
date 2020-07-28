package com.example.leaveform;

class History {
    String from,to,Reason;

    public History() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public History(String from, String to, String reason) {
        this.from = from;
        this.to = to;
        Reason = reason;
    }
}

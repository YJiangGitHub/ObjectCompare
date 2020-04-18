public class ResultDetail {
    private String key;
    private Object actual;
    private Object expect;
    private String retMsg;

    public ResultDetail() {}

    public ResultDetail(String key, Object actual, Object expect, String retMsg) {
        this.key = key;
        this.actual = actual;
        this.expect = expect;
        this.retMsg = retMsg;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public void setActual(Object actual) {
        this.actual = actual;
    }
    public void setExpect(Object expect) {
        this.expect = expect;
    }
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getKey() {
        return key;
    }
    public Object getActual() {
        return actual;
    }
    public Object getExpect() {
        return expect;
    }
    public String getRetMsg() {
        return retMsg;
    }


    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("CompareBaseResult{");
        stringBuffer.append("key=").append(this.key);
        stringBuffer.append(", expect=").append(this.expect);
        stringBuffer.append(", actual=").append(this.actual);
        stringBuffer.append(", retMsg=").append(this.retMsg);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

}

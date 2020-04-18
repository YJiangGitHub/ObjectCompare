import java.io.Serializable;
import java.util.List;

public class BaseResult implements Serializable {
    private List<ResultDetail> resultDetail;
    private int wrongNumber;

    public BaseResult() {}

    public List<ResultDetail> getResultDetail() {
        return resultDetail;
    }
    public int getWrongNumber() {
        return wrongNumber;
    }

    public void setWrongNumber(int wrongNumber) {
        this.wrongNumber = wrongNumber;
    }
    public void setResultDetail(List<ResultDetail> resultDetail) {
        this.resultDetail = resultDetail;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("CompareBaseResult{");
        stringBuffer.append("wrongNumber=").append(this.wrongNumber);
        stringBuffer.append(", resultDetail=").append(this.resultDetail);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

}


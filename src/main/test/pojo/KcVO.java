package pojo;

/**
 * Created by Administrator on 2017/2/28.
 */
public class KcVO {
    private String id;
    private String kcname;
    private String typeScore;
    private String sTime;
    private String className;

    @Override
    public String toString() {
        return "KcVO{" +
                "id='" + id + '\'' +
                ", kcname='" + kcname + '\'' +
                ", typeScore='" + typeScore + '\'' +
                ", sTime='" + sTime + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KcVO kcVO = (KcVO) o;

        if (id != null ? !id.equals(kcVO.id) : kcVO.id != null) return false;
        if (kcname != null ? !kcname.equals(kcVO.kcname) : kcVO.kcname != null) return false;
        if (typeScore != null ? !typeScore.equals(kcVO.typeScore) : kcVO.typeScore != null) return false;
        if (sTime != null ? !sTime.equals(kcVO.sTime) : kcVO.sTime != null) return false;
        return !(className != null ? !className.equals(kcVO.className) : kcVO.className != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (kcname != null ? kcname.hashCode() : 0);
        result = 31 * result + (typeScore != null ? typeScore.hashCode() : 0);
        result = 31 * result + (sTime != null ? sTime.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKcname() {
        return kcname;
    }

    public void setKcname(String kcname) {
        this.kcname = kcname;
    }

    public String getTypeScore() {
        return typeScore;
    }

    public void setTypeScore(String typeScore) {
        this.typeScore = typeScore;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

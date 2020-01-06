package cn.bx.bid.entity;


import java.io.Serializable;
import java.util.*;


public class Professor implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String professorname;

    private String gender;

    private Date birthday;

    private String identitynumber;

    private String workstatus;

    private String institution;

    private String education;

    private String major;

    private String employcompany;

    private String administrative;

    private String technicalpost;

    private String phone;

    private String mobliephone;

    private String address;

    private String projectcategory;

    private String createby;

    private Date createdate;

    private String modifyby;

    private Date modifydate;

    private Byte workdate;

    private Integer lostnum;

    private List<WorkHistory> workhistory=new ArrayList<>(0);
    public Professor() {

    }

    public Professor(Long id, String professorname, String gender, Date birthday, String identitynumber, String workstatus, String institution,
                     String education, String major, String employcompany, String administrative, String technicalpost, String phone,
                     String mobliephone, String address, String projectcategory, String createby, Date createdate, String modifyby, Date modifydate,
                     Byte workdate, Integer lostnum) {
        super();
        this.id = id;
        this.professorname = professorname;
        this.gender = gender;
        this.birthday = birthday;
        this.identitynumber = identitynumber;
        this.workstatus = workstatus;
        this.institution = institution;
        this.education = education;
        this.major = major;
        this.employcompany = employcompany;
        this.administrative = administrative;
        this.technicalpost = technicalpost;
        this.phone = phone;
        this.mobliephone = mobliephone;
        this.address = address;
        this.projectcategory = projectcategory;
        this.createby = createby;
        this.createdate = createdate;
        this.modifyby = modifyby;
        this.modifydate = modifydate;
        this.workdate = workdate;
        this.lostnum = lostnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfessorname() {
        return professorname;
    }

    public void setProfessorname(String professorname) {
        this.professorname = professorname==null ? null : professorname.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender==null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdentitynumber() {
        return identitynumber;
    }

    public void setIdentitynumber(String identitynumber) {
        this.identitynumber = identitynumber==null ? null : identitynumber.trim();
    }

    public String getWorkstatus() {
        return workstatus;
    }

    public void setWorkstatus(String workstatus) {
        this.workstatus = workstatus==null ? null : workstatus.trim();
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution==null ? null : institution.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education==null ? null : education.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major==null ? null : major.trim();
    }

    public String getEmploycompany() {
        return employcompany;
    }

    public void setEmploycompany(String employcompany) {
        this.employcompany = employcompany==null ? null : employcompany.trim();
    }

    public String getAdministrative() {
        return administrative;
    }

    public void setAdministrative(String administrative) {
        this.administrative = administrative==null ? null : administrative.trim();
    }

    public String getTechnicalpost() {
        return technicalpost;
    }

    public void setTechnicalpost(String technicalpost) {
        this.technicalpost = technicalpost==null ? null : technicalpost.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone==null ? null : phone.trim();
    }

    public String getMobliephone() {
        return mobliephone;
    }

    public void setMobliephone(String mobliephone) {
        this.mobliephone = mobliephone==null ? null : mobliephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address==null ? null : address.trim();
    }

    public String getProjectcategory() {
        return projectcategory;
    }

    public void setProjectcategory(String projectcategory) {
        this.projectcategory = projectcategory==null ? null : projectcategory.trim();
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby==null ? null : createby.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby==null ? null : modifyby.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public Byte getWorkdate() {
        return workdate;
    }

    public void setWorkdate(Byte workdate) {
        this.workdate = workdate;
    }

    public Integer getLostnum() {
        return lostnum;
    }

    public void setLostnum(Integer lostnum) {
        this.lostnum = lostnum;
    }

    public List<WorkHistory> getWorkhistory() {
        return workhistory;
    }

    public void setWorkhistory(List<WorkHistory> workhistory) {
        this.workhistory = workhistory;
    }

    @Override
    public String toString() {
        return "Professor [id="+id+", professorname="+professorname+", gender="+gender+", birthday="+birthday+", identitynumber="+identitynumber
               +", workstatus="+workstatus+", institution="+institution+", education="+education+", major="+major+", employcompany="+employcompany
               +", administrative="+administrative+", technicalpost="+technicalpost+", phone="+phone+", mobliephone="+mobliephone+", address="+address
               +", projectcategory="+projectcategory+", createby="+createby+", createdate="+createdate+", modifyby="+modifyby+", modifydate="
               +modifydate+", workdate="+workdate+", lostnum="+lostnum+"]";
    }
}
package cn.bx.bid.entity;

public class ProfessorEnroll {
    // private long id;

     private Professor prof=new Professor();
     private RandomName rand=new RandomName();

    /* public long getId() {
          return id;
     }

     public void setId(long id) {
          this.id = id;
     }*/

     public Professor getProf() {
          return prof;
     }

     public void setProf(Professor prof) {
          this.prof = prof;
     }

     public RandomName getRand() {
          return rand;
     }

     public void setRand(RandomName rand) {
          this.rand = rand;
     }
}

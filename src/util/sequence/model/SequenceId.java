/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.sequence.model;

/**
 *
 * @author vishal
 */
public class SequenceId {

    private String id;

    private long seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "SeuqenceId{" + "id=" + id + ", seq=" + seq + '}';
    }

}

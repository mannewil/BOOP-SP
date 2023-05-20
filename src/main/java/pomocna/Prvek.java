/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pomocna;

/**
 *
 * @author wille
 */
public class Prvek<E> implements java.io.Serializable {

    private static final long serialVersionUID = 2L;
    public E data; //data
    public Prvek<E> next; //pointer

    public Prvek(E data, Prvek<E> next) {

        this.data = data;
        this.next = next;

    }

}

package ua.nure.kharkov.practice2;

import java.util.Arrays;
import java.util.Iterator;
public class MyListImpl implements MyList,ListIterable {

	 private Object[] el = new Object[10];
	    private boolean clear = false;
	    private int count =0;

	    @Override
	    public String toString(){
	        if (count != 0){
	           toArray();
	        }
	        return Arrays.toString(el);
	    }

	    @Override
	    public void add(Object e) {
	        if (clear){
	            el = new Object[10];
	            clear = false;
	        }
	        if (count >= el.length-1){
	            Object[] newEl = new Object[el.length*2];
	            System.arraycopy(el, 0, newEl, 0, count);
	            el = newEl;
	        }
	            el[count++]=e;
	    }
	    //очистка, путем создания пустого массива и обнуления счетчика
	    @Override
	    public void clear() {
	        el = new Object[0];
	        count = 0;
	        clear = true;
	    }
	    //предварительно убрав пустые ячейки(toArray()) перебирает
	    //все элементы и записывает в новый массив (работает счетчик),если находит
	    //элемент который нужно удалить, пропускает шаг записи
	    //в конце проверяется счетчик (удалился ли элемент)
	    @Override
	    public boolean remove(Object o) {
	        if (contains(o)){
	            toArray();
	            int k = 0;
	            Object[] newEl = new Object[el.length];
	        for (Object anEl : el) {
	            if (anEl!=null & o!=null){
	                if (o.equals(anEl)) {
	                    continue;
	                }} else if (o==null & anEl==null)
	            {
	                continue;
	            }
	            newEl[k] = anEl;
	            k++ ;
	        }
	        if (k < count) {
	            el = newEl;
	            count--;
	        }
	            return true;
	        }else return false;
	    }
	    //возвращает массив элементов, при этом убирает запас в виде пустых значений
	    @Override
	    public Object[] toArray() {
	        Object[] newEl = new Object[count];
	        System.arraycopy(el, 0, newEl, 0, count);
	        el = newEl;
	        return el;
	    }

	    @Override
	    public int size() {
	        return count;
	    }

	    @Override
	    public boolean contains(Object o) {
	        boolean rezult = false;
	        for (Object anEl : el) {
	            if (anEl!=null & o!=null){
	                if (o.equals(anEl)) {
	                rezult = true;
	                break;
	            }} else if (o==null & anEl==null)
	                {
	                    rezult = true;
	                }
	        }
	        return rezult;
	    }
	    //Проверяет, находятся ли все объекты с принимаемого листа
	    //в текущем.Вызывается метод поиска в данном листе.
	    @Override
	    public boolean containsAll(MyList c) {
	        for (Object cEl : c.toArray()) {
	            if (!contains(cEl)){
	                return false;
	            }
	        }
	        return true;
	    }


	    @Override
	    public Iterator<Object> iterator() {

	        return new IteratorImpl();
	    }
	    //функия связующее для удаления элемента
	    private void removed(Object t){
	        remove(t);
	    }

	    private class IteratorImpl implements Iterator<Object> {
	        protected int k = 0;
	        protected boolean flag;
	        // returns true if the iteration has more elements
	        public boolean hasNext() {
	            if (k<count)
	            {
	                return true;
	            }
	            else
	                {
	                    return false;
	                }
	        }
	        // returns the next element in the iteration
	        public Object next() {
	            flag = true;
	            return el[k++];
	        }

	        // removes from the underlying collection the last element
	        //returned by this iterator
	        public void remove() {
	            if(!flag) {
	                throw new IllegalStateException();
	            } else {
	                flag = false;
	                removed(el[--k]);
	            }
	        }
	    }
	    public ListIterator listIterator() {
	        return new ListIteratorImpl();
	    }

	    private class ListIteratorImpl extends IteratorImpl implements ListIterator {
	        private boolean flag1;
	        @Override
	        public boolean hasPrevious() {
	            if (k>=0){
	                if (k==0){
	                flag1 = false;
	                }
	            }
	            return flag;
	        }

	        @Override
	        public Object previous() {
	            flag = true;
	            if (k==count){
	                k--;
	            }
	            Object ret = el[k--];
	            if(k == -1) flag = false;
	            return ret;
	        }

	        @Override
	        public void set(Object e) {

	        }
	    }


}

import java.util.*;

public class InventoryManager {

        private final Map<Integer, List<PhysicalBook>> inventory;
        private int nextCopyId = 1;

        InventoryManager() {
            inventory = new HashMap<>();
        }

        public List<PhysicalBook> addCopies( Book book ,int copies ){
            if( copies == 0 ) return Collections.emptyList();

            List<PhysicalBook> books = inventory.computeIfAbsent(book.getBookId(), p -> new ArrayList<>());
            List<PhysicalBook> created = new ArrayList<>(copies);

            for( int i = 0; i < copies ; i++ ) {
                PhysicalBook pBook  = new PhysicalBook( nextCopyId++, book);
                pBook.setStatus(COPY_STATUS.AVAILABLE);
                books.add( pBook );
                created.add( pBook );
            }
            return created;
        }

        public List<PhysicalBook> removeCopies( Book book , int count ){
            int id = book.getBookId();
            List<PhysicalBook> books = inventory.get( id );
            List<PhysicalBook> result = new ArrayList<>();

            if( count == 0 || books == null || books.isEmpty()) return result;

            for( int i = books.size() -1 ; i >= 0 && result.size() < count ; i-- ){
                PhysicalBook book1 = books.get(i);
                if( book1.getStatus() == COPY_STATUS.AVAILABLE ){
                    book1.setStatus(COPY_STATUS.REMOVED);
                    result.add(book1);
                    books.remove(book1);
                }
            }
            if( books.isEmpty() )
                inventory.remove(book.getBookId());

            return result;
        }

    public synchronized boolean allocateCopy(int copyId) {
        PhysicalBook p = findCopy(copyId);
        if (p == null || p.getStatus() != COPY_STATUS.AVAILABLE) return false;
        p.setStatus(COPY_STATUS.ISSUED);
        return true;
    }

    public synchronized boolean returnCopy(int copyId) {
        PhysicalBook p = findCopy(copyId);
        if (p == null) return false;
        p.setStatus(COPY_STATUS.AVAILABLE);
        return true;
    }

    public Optional<PhysicalBook> getAnyAvailableCopy(int bookId) {
        List<PhysicalBook> list = inventory.get(bookId);
        if (list == null) return Optional.empty();
        return list.stream().filter(p -> p.getStatus() == COPY_STATUS.AVAILABLE).findFirst();
    }

    public synchronized List<PhysicalBook> getAllCopies(int bookId) {
        return inventory.getOrDefault(bookId, Collections.emptyList());
    }

    public PhysicalBook findCopy(int copyId) {
        for (List<PhysicalBook> list : inventory.values()) {
            for (PhysicalBook p : list) {
                if (Objects.equals(p.getCopyId(), copyId)) return p;
            }
        }
        return null;
    }

}

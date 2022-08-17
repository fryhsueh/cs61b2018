public class OffByN implements CharacterComparator {
    private int diff;

    public OffByN(int N) {
        diff = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        // TODO Auto-generated method stub
        int d = Math.abs(x - y);
        return d == diff;

    }
    
}

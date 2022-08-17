public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        // TODO Auto-generated method stub
        int diff = x - y;
        return Math.abs(diff) == 1;
    }

    
    
}

package FileSystem;

public class File  extends FileSystemNode {
    private String content;

    public File( String name, Directory parent, String content ){
        super(name,parent);
        this.content = content;
    }

    public String read(){
        return content;
    }

    public void write( String content ){
        this.content = content;
    }

    @Override
    protected FileSystemNode deepCopy(Directory newParent) {
        return new File(this.name, newParent, this.read());
    }

    @Override
    public int getSize() {
        return content == null ? 0 : content.length();
    }
}

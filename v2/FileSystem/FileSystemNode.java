package FileSystem;

public abstract class FileSystemNode {
    protected String name;
    protected Directory parent;

    public FileSystemNode(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public String getPath(){
        if( parent == null ){
            return "/";
        }

        String parentPath = parent.getPath();
        if("/".equals(parentPath)){
            return parentPath + name;
        }
        return parentPath + "/" + name;
    }

    protected void setParent(Directory parent) {
        this.parent = parent;
    }

    protected abstract FileSystemNode deepCopy(Directory newParent);
    public abstract int getSize();
}



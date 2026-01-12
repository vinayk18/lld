package FileSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory extends FileSystemNode {

    private Map<String,FileSystemNode> children;

    public Directory( String name, Directory parent ){
        super(name,parent);
        this.children = new HashMap<>();
    }

    public void addChild( FileSystemNode node ){
        if(children.containsKey(node.getName())){
            throw new IllegalArgumentException("Node already exists: "+  node.getName());
        }
        children.put(node.getName(), node);
    }

    public FileSystemNode getChild( String name){
        return children.get(name);
    }

    public void removeChild( String name){
        if(!children.containsKey(name)){
            throw new IllegalArgumentException("No such file or directory: "+ name );
        }
        children.remove(name);
    }

    public List<String> listChildren(){
        return new ArrayList<>(children.keySet());
    }

    public boolean isEmpty(){
        return children.isEmpty();
    }

    @Override
    protected FileSystemNode deepCopy(Directory newParent) {
        Directory copy = new Directory(this.name, newParent);

        for (String childName : this.listChildren()) {
            FileSystemNode child = this.getChild(childName);
            FileSystemNode childCopy = child.deepCopy(copy);
            copy.addChild(childCopy);
        }

        return copy;
    }

    @Override
    public int getSize() {
        int totalSize = 0;

        for (String childName : listChildren()) {
            FileSystemNode child = getChild(childName);
            totalSize += child.getSize();
        }

        return totalSize;
    }
}

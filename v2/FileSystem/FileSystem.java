package FileSystem;

import java.util.List;

public class FileSystem {
    private Directory root;

    public FileSystem(){
        this.root = new Directory("",null);
    }

    private static class PathInfo {
        Directory parentDir;
        String name;

        PathInfo(Directory parentDir, String name) {
            this.parentDir = parentDir;
            this.name = name;
        }
    }


    private Directory traverseToDirectory( String path ){
        if(!path.startsWith("/")){
            throw new IllegalArgumentException("Invalid path: "+  path );
        }
        String[] parts = path.split("/");
        Directory cur = root;
        for( int i = 1;i < parts.length; i++ ){
            String part = parts[i];
            if( part.isEmpty()) continue;

            FileSystemNode node = cur.getChild(part);
            if( node == null || !(node instanceof Directory)){
                throw new IllegalArgumentException("Invalid directory path: " + path );
            }
            cur = (Directory) node;
        }
        return cur;
    }

    private PathInfo resolveParent(String path) {
        if (!path.startsWith("/")) {
            throw new IllegalArgumentException("Invalid path: " + path);
        }

        int idx = path.lastIndexOf('/');
        if (idx <= 0 || idx == path.length() - 1) {
            throw new IllegalArgumentException("Invalid path: " + path);
        }

        String parentPath = path.substring(0, idx);
        String name = path.substring(idx + 1);

        Directory parentDir = parentPath.isEmpty()
                ? root
                : traverseToDirectory(parentPath);

        return new PathInfo(parentDir, name);
    }


    public void mkdir(String path){
        PathInfo info = resolveParent(path);
        info.parentDir.addChild(new Directory(info.name, info.parentDir));
    }

    public void addFile( String path, String content){
      PathInfo info = resolveParent(path);
      info.parentDir.addChild(new File(info.name,info.parentDir,content));
    }
    public List<String> ls(String path){
        if("/".equals(path))
            root.listChildren();

        PathInfo info = resolveParent(path);
        FileSystemNode node = info.parentDir.getChild(info.name);

        if (node == null || !(node instanceof Directory)) {
            throw new IllegalArgumentException("Not a directory: " + path);
        }
        return ((Directory) node).listChildren();
    }
    public String readFile(String path) {
        PathInfo info = resolveParent(path);
        FileSystemNode node = info.parentDir.getChild(info.name);

        if (node == null || !(node instanceof File)) {
            throw new IllegalArgumentException("Not a file: " + path);
        }

        return ((File) node).read();
    }

    public void rm(String path) {
        if ("/".equals(path)) {
            throw new IllegalArgumentException("Cannot delete root");
        }

        PathInfo info = resolveParent(path);
        info.parentDir.removeChild(info.name);
    }

    public void move(String srcPath, String destPath) {
        PathInfo srcInfo = resolveParent(srcPath);
        PathInfo destInfo = resolveParent(destPath);

        FileSystemNode node = srcInfo.parentDir.getChild(srcInfo.name);
        if (node == null) {
            throw new IllegalArgumentException("Source does not exist: " + srcPath);
        }

        if (destInfo.parentDir.getChild(destInfo.name) != null) {
            throw new IllegalArgumentException("Destination already exists: " + destPath);
        }

        // Remove from source
        srcInfo.parentDir.removeChild(srcInfo.name);

        // Update parent and name
        node.setParent(destInfo.parentDir);
        node.name = destInfo.name;

        // Add to destination
        destInfo.parentDir.addChild(node);
    }

    public void copy(String srcPath, String destPath) {
        PathInfo srcInfo = resolveParent(srcPath);
        PathInfo destInfo = resolveParent(destPath);

        FileSystemNode srcNode = srcInfo.parentDir.getChild(srcInfo.name);
        if (srcNode == null) {
            throw new IllegalArgumentException("Source does not exist: " + srcPath);
        }

        if (destInfo.parentDir.getChild(destInfo.name) != null) {
            throw new IllegalArgumentException("Destination already exists: " + destPath);
        }

        FileSystemNode copiedNode = srcNode.deepCopy(destInfo.parentDir);
        copiedNode.name = destInfo.name;

        destInfo.parentDir.addChild(copiedNode);
    }

    public int size(String path) {
        if ("/".equals(path)) {
            return root.getSize();
        }

        PathInfo info = resolveParent(path);
        FileSystemNode node = info.parentDir.getChild(info.name);

        if (node == null) {
            throw new IllegalArgumentException("Path does not exist: " + path);
        }

        return node.getSize();
    }
}

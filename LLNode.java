
public class LLNode<T> {
	
	private T nodeData;
	private LLNode<T> nextNode;
	
	public LLNode(T data){
		this.nodeData = data;	
	}

	public T getNodeData(){
		return nodeData;	
	}
	
	public void setNodeData(T data){
		this.nodeData = data;
	}
	
	public LLNode<T> getNext(){
		return this.nextNode;
	}
	
	public void setNext(LLNode<T> node){
		this.nextNode = node;
	}
	
}

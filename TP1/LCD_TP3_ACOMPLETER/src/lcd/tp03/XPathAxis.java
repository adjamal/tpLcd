package lcd.tp03;

import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.Vector;
import java.util.function.Function;

import org.w3c.dom.Node;


class NodeComparator implements Comparator<Node> {
			public int compare(Node n1, Node n2) {
			int pre1 = (Integer) n1.getUserData("preorder");
			int pre2 = (Integer) n2.getUserData("preorder");
			if (pre1 < pre2) return -1;
			else if (pre1 > pre2) return 1;
			else return 0;
}
}

class XPathAxis extends XPathExpr {
	private String axis;
	private static NodeComparator nodeComp =
							new NodeComparator(); 
	XPathAxis(String axis) {
		super(1);
		this.axis = axis;
	}


	@Override
	Vector<Node> eval() {
		Vector<Node> arg = this.arguments[0].eval();
		Vector<Node> res = new Vector<>();
		/****
		 * A COMPLETER : res doit contenir l'application de this.axis à chacun des nœuds dans arg.
		 * res doit être ordonné dans l'ordre du document et sans doublon.
		 */
		
		
		switch (this.axis) {
		case "self":
			res = arg;
			break;
			
		case "child":
			for(Node n : arg) {
				for(Node child = n.getFirstChild();
					child != null;
					child = child.getNextSibling()) {
					res.add(child);
				}
						
			}
			
			Collections.sort(res, nodeComp);
			
			break;
			
		case "parent":
			/**
			 * A COMPLETER
			 */
			break;
			
		case "descendant":
			
			TreeSet<Node> set = new TreeSet<>(nodeComp);
			
			for(Node n : arg) {
				set.addAll(XPathExpr.getDescendants(n, false));						
			}

			res.addAll(set);
			
			break;
			
		case "ancestor":
			/**
			 * A COMPLETER
			 */
			break;
			
		default:
			;
		}
		
		return res;
	}

	@Override
	String getLabel() {
		return axis;
	}

}

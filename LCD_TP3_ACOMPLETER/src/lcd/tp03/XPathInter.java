package lcd.tp03;

import java.util.Vector;

import org.w3c.dom.Node;

class XPathInter extends XPathExpr {

	
	XPathInter () { super (2); }
	@Override
	Vector<Node> eval() {
		Vector<Node> set1 = arguments[0].eval();
		Vector<Node> set2 = arguments[1].eval();
		Vector<Node> res = new Vector<>();
		
		/****
		 * A COMPLETER : res doit contenir l'intersection des deux vecteurs set1 et set2 (qui sont triés dans l'ordre du document)
		 * Le numéro prefixe d'un nœud peut s'obtenir grace à
		 * Integer pre = (Integer) n.getUserData("preorder");
		 */
		int i = 0;
		int j = 0;
		while (i < set1.size() && j < set2.size()) {
		
			//Recuperer les identifiants de chaque noeuds	
			
			Node n1 = set1.get(i);
		
			Node n2 = set2.get(j);
			
		  //Ensuite la comparaison
			
			
			Integer pre1 = (Integer) n1.getUserData("preorder");
			Integer pre2 = (Integer) n2.getUserData("preorder");
		
			if (pre1.equals(pre2)) {
				res.add(n1);
				i++;
				j++;
			} else if (pre1 < pre2) {
				i++;
			} else {
				j++;
			}
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
					
		return res;
		
	}
	@Override
	String getLabel() {
		// TODO Auto-generated method stub
		return "Inter";
	}

}

package itac.textrank;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.MapTransformer;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.scoring.PageRankWithPriors;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

/**
 *
 */
public class TextRank<V, E> extends PageRankWithPriors<V, E> {
    protected boolean done;
    
    /**
     * Creates an instance for the specified graph, edge weights, and random jump probability.
     * @param graph the input graph
     * @param edge_weight the edge weights (transition probabilities)
     * @param alpha the probability of taking a random jump to an arbitrary vertex
     */
    public TextRank(Graph<V, E> graph,
                    Transformer<E, ? extends Number> edge_weights,
                    Transformer<V, Double> vertex_priors, double alpha)
    {
        super(graph, edge_weights, vertex_priors, alpha);
    }

    /**
     * Creates an instance for the specified graph and random jump probability; the probability
     * of following any outgoing edge from a given vertex is the same.
     * @param graph the input graph
     * @param alpha the probability of taking a random jump to an arbitrary vertex
     */
    public TextRank(Graph<V,E> graph,
                    Transformer<V, Double> vertex_priors, double alpha)
    {
        super(graph, vertex_priors, alpha);
    }

    protected double edgeWeight(V v1, V v2) {
        E e = graph.findEdge(v1, v2);

        return getEdgeWeight(v1, e).doubleValue();
    }

    @Override
    public double update(V v_i)
    {
        collectDisappearingPotential(v_i);
        double sum = 0.0;
        for (V v_j : graph.getPredecessors(v_i)) { // In(v_i)
            double w_ji = edgeWeight(v_j, v_i);

            double succWeights = 0.0;
            for (V v_k : graph.getSuccessors(v_j)) { // Out(v_j)
                double w_jk = edgeWeight(v_j, v_k);
                succWeights += w_jk;
            }

            sum += w_ji / succWeights * getCurrentValue(v_j);
        }

        double newval = (1 - alpha) + alpha * sum;
        setOutputValue(v_i, newval);

        return Math.abs(getCurrentValue(v_i) - newval);
    }

    public boolean done()
    {
        return done || super.done();
    }

    protected void updateMaxDelta(V v, double diff)
    {
        if (diff < tolerance) 
            done = true;
        
        super.updateMaxDelta(v, diff);
    }
 
    public static void main(String[] args) {
        final UndirectedSparseGraph<String, String> g = new UndirectedSparseGraph<String, String>();

        final HashMap<String, Double> priors = new HashMap<String, Double>();
        @SuppressWarnings("serial")
        HashMap<String, Double> weights = new HashMap<String, Double>() {
            private void addVert(String v, double prior) {
                priors.put(v, prior);
                g.addVertex(v);
            }
            private void addEdge(String v1, String v2, double weight) {
                String e = v1 + "--" + v2;
                g.addEdge(e, new Pair<String>(v1, v2), EdgeType.UNDIRECTED);
                put(e, weight);
            }{
                //      v       prior
                addVert("this", 1.0);
                addVert("is",   1.0);
                addVert("a",    1.0);
                addVert("test", 1.0);
                //      v1      v2      weight
                addEdge("this", "is",   0.1);
                addEdge("is",   "a",    1.0);
                addEdge("a",    "test", 0.4);
                addEdge("this", "test", 0.4);
                addEdge("is",   "test", 4.7);
            }
        };

        final TextRank<String, String> t = new TextRank<String, String>(
                g,
                MapTransformer.getInstance(weights),
                MapTransformer.getInstance(priors),
                0.85);
        // Konvergenzschwelle wie im Paper setzen:
        t.setTolerance(0.0001);
        t.setMaxIterations(1);
        t.evaluate();
        
        System.out.println(t.getVertexScore("test"));
        System.out.println(t.getVertexScore("this"));
        System.out.println(t.getVertexScore("is"));
        System.out.println(t.getVertexScore("a"));
        
        Layout<String, String> layout = new FRLayout<String, String>(g);
        layout.setSize(new Dimension(800,600));
        BasicVisualizationServer<String, String> vv = new BasicVisualizationServer<String, String>(layout);
        vv.setPreferredSize(new Dimension(600,600)); //Sets the viewing area size
        vv.getRenderContext().setVertexLabelTransformer(new Transformer<String, String>() {
            public String transform(String s) {
                return String.format("%s [%.3f]", s, t.getVertexScore(s));
            }
        });

        JFrame frame = new JFrame("TextRank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }
}

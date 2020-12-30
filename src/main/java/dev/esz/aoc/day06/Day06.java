package dev.esz.aoc.day06;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.*;

public interface Day06 {
    static int part1(List<String> lines) {
        Map<String, TreeNode> nodeMap = createTree(lines);
        return dfs(nodeMap.get("COM"), 0);
    }

    static int part2(List<String> lines) {
        Map<String, TreeNode> nodeMap = createTree(lines);

        Queue<TreeNode> roadToYou = dfsWithRoute(nodeMap.get("COM"), nodeMap.get("YOU"), new ArrayDeque<>());
        Queue<TreeNode> roadToSanta = dfsWithRoute(nodeMap.get("COM"), nodeMap.get("SAN"), new ArrayDeque<>());
        while (roadToYou.peek() == roadToSanta.peek() && !roadToYou.isEmpty()) {
            roadToYou.poll();
            roadToSanta.poll();
        }

        return roadToYou.size() + roadToSanta.size();
    }

    private static Map<String, TreeNode> createTree(List<String> lines) {
        Map<String, TreeNode> nodeMap = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split("\\)");
            String parent = parts[0];
            String child = parts[1];
            TreeNode parentNode = nodeMap.compute(
                    parent, (key, node) -> Objects.requireNonNullElseGet(node, () -> new TreeNode(key, new ArrayList<>())));
            TreeNode childNode = nodeMap.compute(
                    child, (key, node) -> Objects.requireNonNullElseGet(node, () -> new TreeNode(key, new ArrayList<>())));
            parentNode.addChildren(childNode);
        }

        return nodeMap;
    }

    private static int dfs(TreeNode root, int depth) {
        int d = depth;
        for (TreeNode child : root.getChildren()) {
            d += dfs(child, depth + 1);
        }
        return d;
    }

    private static Queue<TreeNode> dfsWithRoute(TreeNode root, TreeNode destination, Deque<TreeNode> currentRoute) {
        Queue<TreeNode> destinationRoute = new ArrayDeque<>();
        if (root == destination) {
            return new ArrayDeque<>(currentRoute);
        } else {
            currentRoute.add(root);
            for (TreeNode child : root.getChildren()) {
                destinationRoute = dfsWithRoute(child, destination, currentRoute);
                if (!destinationRoute.isEmpty()) {
                    break;
                }
            }
            currentRoute.pollLast();
        }
        return destinationRoute;
    }
}

@Value
class TreeNode {
    String name;
    List<TreeNode> children;

    public void addChildren(TreeNode treeNode) {
        children.add(treeNode);
    }
}

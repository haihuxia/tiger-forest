package com.xhh.demo.zookeeper.cache;

import com.xhh.demo.zookeeper.constant.CommonString;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

/**
 * 树节点缓存
 *
 * <p>相当于 PathCache 和 NodeCach 的组合
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/17 上午9:38
 * @since 1.6
 */
public class TreeCacheTest {

    private static final String PATH = "/example/treeCache";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = null;
        TreeCache cache = null;
        try {
            client = CuratorFrameworkFactory.newClient(CommonString.CONNECT_STR, new ExponentialBackoffRetry(1000, 3));
            client.start();
            cache = new TreeCache(client, PATH);
            cache.start();

            processCommands(client, cache);
        } finally {
            CloseableUtils.closeQuietly(cache);
            CloseableUtils.closeQuietly(client);
        }
    }

    /**
     * 增加监听
     *
     * @param cache 缓存
     */
    private static void addListener(final TreeCache cache) {
        TreeCacheListener listener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case NODE_ADDED: {
                        System.out.println("TreeNode added: " + ZKPaths.getNodeFromPath(event.getData().getPath()) + ", value: "
                                + new String(event.getData().getData()));
                        break;
                    }
                    case NODE_UPDATED: {
                        System.out.println("TreeNode changed: " + ZKPaths.getNodeFromPath(event.getData().getPath()) + ", value: "
                                + new String(event.getData().getData()));
                        break;
                    }
                    case NODE_REMOVED: {
                        System.out.println("TreeNode removed: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
                        break;
                    }
                    default:
                        System.out.println("Other event: " + event.getType().name());
                }
            }
        };
        cache.getListenable().addListener(listener);
    }

    /**
     * 输入命令
     *
     * @param client 客户端
     * @param cache 缓存
     * @throws Exception
     */
    private static void processCommands(CuratorFramework client, TreeCache cache) throws Exception {
        printHelp();
        try {
            addListener(cache);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            boolean done = false;
            while (!done) {
                System.out.print("> ");
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                String command = line.trim();
                String[] parts = command.split("\\s");
                if (parts.length == 0) {
                    continue;
                }
                String operation = parts[0];
                String args[] = Arrays.copyOfRange(parts, 1, parts.length);
                if (operation.equalsIgnoreCase("help") || operation.equalsIgnoreCase("?")) {
                    printHelp();
                } else if (operation.equalsIgnoreCase("q") || operation.equalsIgnoreCase("quit")) {
                    done = true;
                } else if (operation.equals("set")) {
                    setValue(client, command, args);
                } else if (operation.equals("remove")) {
                    remove(client, command, args);
                } else if (operation.equals("list")) {
                    list(cache);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示所有缓存信息
     *
     * @param cache 缓存
     */
    private static void list(TreeCache cache) {
        if (cache.getCurrentChildren(PATH).size() == 0) {
            System.out.println("* empty *");
        } else {
            for (Map.Entry<String, ChildData> entry : cache.getCurrentChildren(PATH).entrySet()) {
                System.out.println(entry.getKey() + " = " + new String(entry.getValue().getData()));
            }
        }
    }

    /**
     * 删除节点
     *
     * @param client 客户端
     * @param command 命令
     * @param args 参数
     * @throws Exception
     */
    private static void remove(CuratorFramework client, String command, String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("syntax error (expected remove <path>): " + command);
            return;
        }
        String name = args[0];
        if (name.contains("/")) {
            System.err.println("Invalid node name" + name);
            return;
        }
        String path = ZKPaths.makePath(PATH, name);
        try {
            client.delete().forPath(path);
        } catch (KeeperException.NoNodeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置值
     *
     * @param client 客户端
     * @param command 命令
     * @param args 参数
     * @throws Exception
     */
    private static void setValue(CuratorFramework client, String command, String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("syntax error (expected set <path> <value>): " + command);
            return;
        }
        String name = args[0];
        if (name.contains("/")) {
            System.err.println("Invalid node name" + name);
            return;
        }
        String path = ZKPaths.makePath(PATH, name);
        byte[] bytes = args[1].getBytes();
        try {
            client.setData().forPath(path, bytes);
        } catch (KeeperException.NoNodeException e) {
            client.create().creatingParentsIfNeeded().forPath(path, bytes);
        }
    }

    /**
     * 显示帮助信息
     */
    private static void printHelp() {
        System.out.println("An example of using PathChildrenCache. This example is driven by entering commands at the prompt:\n");
        System.out.println("set <name> <value>: Adds or updates a node with the given name");
        System.out.println("remove <name>: Deletes the node with the given name");
        System.out.println("list: List the nodes/values in the cache");
        System.out.println("quit: Quit the example");
        System.out.println();
    }
}

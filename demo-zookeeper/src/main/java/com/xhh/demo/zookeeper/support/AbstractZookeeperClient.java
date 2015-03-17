package com.xhh.demo.zookeeper.support;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import com.xhh.demo.zookeeper.ChildListener;
import com.xhh.demo.zookeeper.StateListener;
import com.xhh.demo.zookeeper.ZookeeperClient;
import lombok.extern.slf4j.Slf4j;

/**
 * zkClient 扩展
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/13 下午8:05
 * @since 1.6
 */
@Slf4j
public abstract class AbstractZookeeperClient<TargetChildListener> implements ZookeeperClient {

    /** 连接参数 */
	private final URL url;

    /** 状态监听 set */
	private final Set<StateListener> stateListeners = new CopyOnWriteArraySet<StateListener>();

    /** 子节点监听 ConcurrentMap */
	private final ConcurrentMap<String, ConcurrentMap<ChildListener, TargetChildListener>> childListeners
            = new ConcurrentHashMap<String, ConcurrentMap<ChildListener, TargetChildListener>>();

    /** 连接关闭状态 */
	private volatile boolean closed = false;

    /**
     * 构造函数
     *
     * @param url 连接字符串
     */
	public AbstractZookeeperClient(URL url) {
		this.url = url;
	}

    /**
     * 获取连接参数
     *
     * @return 连接参数
     */
	public URL getUrl() {
		return url;
	}

    /**
     * 创建节点
     *
     * @param path 路径
     * @param ephemeral 是否为短节点
     */
	public void create(String path, boolean ephemeral) {
		int i = path.lastIndexOf('/');
		if (i > 0) {
			create(path.substring(0, i), false);
		}
		if (ephemeral) {
			createEphemeral(path);
		} else {
			createPersistent(path);
		}
	}

    /**
     * 增加状态监听
     *
     * @param listener 监听
     */
	public void addStateListener(StateListener listener) {
		stateListeners.add(listener);
	}

    /**
     * 删除状态监听
     *
     * @param listener 监听
     */
	public void removeStateListener(StateListener listener) {
		stateListeners.remove(listener);
	}

    /**
     * 获取状态监听列表
     *
     * @return 状态监听列表
     */
	public Set<StateListener> getStateListeners() {
		return stateListeners;
	}

    /**
     * 增加子节点监听
     *
     * @param path 路径
     * @param listener 监听
     * @return 子节点列表
     */
	public List<String> addChildListener(String path, final ChildListener listener) {
		ConcurrentMap<ChildListener, TargetChildListener> listeners = childListeners.get(path);
		if (listeners == null) {
			childListeners.putIfAbsent(path, new ConcurrentHashMap<ChildListener, TargetChildListener>());
			listeners = childListeners.get(path);
		}
		TargetChildListener targetListener = listeners.get(listener);
		if (targetListener == null) {
			listeners.putIfAbsent(listener, createTargetChildListener(listener));
			targetListener = listeners.get(listener);
		}
		return addTargetChildListener(path, targetListener);
	}

    /**
     * 删除子节点监听
     *
     * @param path 路径
     * @param listener 监听
     */
	public void removeChildListener(String path, ChildListener listener) {
		ConcurrentMap<ChildListener, TargetChildListener> listeners = childListeners.get(path);
		if (listeners != null) {
			TargetChildListener targetListener = listeners.remove(listener);
			if (targetListener != null) {
				removeTargetChildListener(path, targetListener);
			}
		}
	}

    /**
     * 调用状态监听处理
     *
     * @param state 状态
     */
	protected void stateChanged(int state) {
		for (StateListener sessionListener : getStateListeners()) {
			sessionListener.stateChanged(state);
		}
	}

    /**
     * 关闭 zkClient
     */
	public void close() {
		if (closed) {
			return;
		}
		closed = true;
		try {
			doClose();
		} catch (Throwable t) {
			log.warn(t.getMessage(), t);
		}
	}

    /**
     * 关闭 zkClient
     */
	protected abstract void doClose();

    /**
     * 创建持久节点
     *
     * @param path 路径
     */
	protected abstract void createPersistent(String path);

    /**
     * 创建临时节点
     *
     * @param path 路径
     */
	protected abstract void createEphemeral(String path);

    /**
     * 创建自定义监听
     *
     * @param listener 监听
     * @return 监听
     */
	protected abstract TargetChildListener createTargetChildListener(ChildListener listener);

    /**
     * 为子节点增加监听
     *
     * @param path 路径
     * @param listener 监听
     * @return 子节点列表
     */
	protected abstract List<String> addTargetChildListener(String path, TargetChildListener listener);

    /**
     * 删除子节点监听
     *
     * @param path 路径
     * @param listener 监听
     */
	protected abstract void removeTargetChildListener(String path, TargetChildListener listener);

}

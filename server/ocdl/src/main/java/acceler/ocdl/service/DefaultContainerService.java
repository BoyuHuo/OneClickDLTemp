package acceler.ocdl.service;

import acceler.ocdl.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DefaultContainerService implements ContainerService {
    private static final Map<User, Integer> assignedContainers = new ConcurrentHashMap<>();
    private final List<Integer> allPorts;


    private int firstPort = 10000;

    private int lastPort = 12000;

    @Value("${local.port.first}")
    public void setFirstPort(int firstPort) {
        this.firstPort = firstPort;
        System.out.println(this.firstPort);
    }

    @Value("${local.port.last}")
    public void setLastPort(int lastPort) {
        this.lastPort = lastPort;
    }

    public DefaultContainerService() {
        System.out.println(this.firstPort);
        this.allPorts = new LinkedList<>();
        List<Integer> unavailablePorts = getUnavailablePorts();

        for (int i = firstPort; i <= lastPort; i++) {
            if (!unavailablePorts.contains(i)) {
                this.allPorts.add(i);
            }
        }
    }

    @Override
    public boolean hasAssignedContainer(final User user) {
        return assignedContainers.containsKey(user);
    }

    @Override
    public Integer getAssignedContainer(final User user) {
        if (!hasAssignedContainer(user)) {
            return null;
        }

        return assignedContainers.get(user);
    }

    @Override
    public List<Integer> getAssignedContainers() {
        List<Integer> assignedPorts = new ArrayList<>();
        for (Integer i : assignedContainers.values()) {
            assignedPorts.add(i);
        }

        return assignedPorts;
    }

    @Override
    public List<Integer> getAvailableContainers() {
        List<Integer> availables = new ArrayList<>();
        for (Integer i : allPorts) {
            if (!assignedContainers.values().contains(i)) {
                availables.add(i);
            }
        }

        return availables;
    }

    @Override
    public Integer requestContainer(final User user) {
        if (assignedContainers.containsKey(user)) {
            return null;
        }

        Integer assign = null;

        synchronized (this) {
            for (Integer i : allPorts) {
                if (!assignedContainers.values().contains(i)) {
                    assign = i;
                    break;
                }
            }
        }

        //TODO:cmd to create container

        CmdHelper.runCommand("docker run -dit -v " +
                "/Users/WBQ/Documents/ALL/PycharmProjects/OneClickDeepLearning/build:/root/build -p "
                + assign + ":8998 oneclick:jupyterpython /bin/bash");

        assignedContainers.put(user,assign);

        return assign;


    }

    @Override
    public void releaseContainer(final User user) {
        //TODO: cmd to release container
        synchronized (this) {
            assignedContainers.remove(user);
        }
    }

    private List<Integer> getUnavailablePorts() {
        // TODO: CmdHelper.runCommand("...");

        ArrayList<Integer> list = new ArrayList<>();
        list.add(8080);
        return list;
        //return null;
    }

}

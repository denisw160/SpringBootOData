package me.wirries.demo.springodata.ui;

/**
 * A functional interface for executing commands.
 *
 * @author denisw
 * @version 1.0
 * @since 23.05.2021
 */
public interface Command {

    /**
     * Execute a command. If the command failed an exception is thrown.
     *
     * @throws Exception Error during execution
     */
    void execute() throws Exception;

}

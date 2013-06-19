package com.hp.hpl.logkv.demo.applet.modelwindow;


import javax.swing.*;


/**
 * This is the 3rd version of SwingWorker (also known as
 * SwingWorker 3), an abstract class that you subclass to
 * perform GUI-related work in a dedicated thread.  For
 * instructions on and examples of using this class, see:
 *
 * http://java.sun.com/docs/books/tutorial/uiswing/misc/threads.html
 *
 * Note that the API changed slightly in the 3rd version:
 * You must now invoke start() on the SwingWorker after
 * creating it.
 */
public abstract class SwingWorker
{
  private Object value; // see getValue(), setValue()

  /**
   * Class to maintain reference to current worker thread
   * under separate synchronization control.
   */
  final private static class WorkerThreadHolder
  {
    private Thread workerThread;

    WorkerThreadHolder(Thread workerThread)
    {
      WorkerThreadHolder.this.workerThread = workerThread;
    }


    synchronized Thread getWorkerThread()
    {
      return workerThread;
    }


    synchronized void clear()
    {
      workerThread = null;
    }
  }



  private WorkerThreadHolder workerThreadHolder;

  /**
   * Get the value produced by the worker thread, or null if it
   * hasn't been constructed yet.
   */
  protected synchronized Object getValue()
  {
    return value;
  }


  /**
   * Set the value produced by worker thread
   */
  private synchronized void setValue(Object obj)
  {
    value = obj;
  }


  /**
   * Compute the value to be returned by the <code>get</code> method.
   */
  public abstract Object construct();


  /**
   * Called on the event dispatching thread (not on the worker thread)
   * after the <code>construct</code> method has returned.
   */
  public void finished()
  {
  }


  /**
   * A new method that interrupts the worker thread.  Call this method
   * to force the worker to stop what it's doing.
   */
  public void interrupt()
  {
    Thread workerThread = workerThreadHolder.getWorkerThread();

    if (workerThread != null)
    {
      workerThread.interrupt();
    }

    workerThreadHolder.clear();
  }


  /**
   * Return the value created by the <code>construct</code> method.
   * Returns null if either the constructing thread or the current
   * thread was interrupted before a value was produced.
   *
   * @return the value created by the <code>construct</code> method
   */
  public Object get()
  {
    while (true)
    {
      Thread workerThread = workerThreadHolder.getWorkerThread();

      if (workerThread == null)
      {
        return getValue();
      }

      try
      {
        workerThread.join();
      }
      catch (InterruptedException ie)
      {
        Thread.currentThread().interrupt(); // propagate

        return null;
      }
    }
  }


  /**
   * Start a thread that will call the <code>construct</code> method
   * and then exit.
   */
  public SwingWorker()
  {
    final Runnable doFinished = new Runnable()
    {
      public void run()
      {
        finished();
      }
    };

    Runnable doConstruct = new Runnable()
    {
      public void run()
      {
        try
        {
          setValue(construct());
        }
        finally
        {
          workerThreadHolder.clear();
        }

        SwingUtilities.invokeLater(doFinished);
      }
    };

    Thread workerThread = new Thread(doConstruct);
    workerThreadHolder = new WorkerThreadHolder(workerThread);
  }


  /**
   * Start the worker thread.
   */
  public void start()
  {
    Thread workerThread = workerThreadHolder.getWorkerThread();

    if (workerThread != null)
    {
      workerThread.start();
    }
  }
}

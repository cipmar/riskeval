package ro.rmc.riskeval.gui;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;


public class SplashWindow extends Window
{

	private static SplashWindow instance;
	
	private Image image;
	
	private boolean paintCalled;
	
	private SplashWindow(Frame parent, Image image)
	{
		super(parent);
		
		this.image = image;
		
		// load the image
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 0);
		
		try
		{
			mt.waitForID(0);
		} catch (InterruptedException e) {}
		
		// abort on failure
		if (mt.isErrorID(0))
		{
			setSize(0, 0);
			System.err.println("Warning: SplashWindow couldn't load splash image.");
			
			synchronized(this)
			{
				paintCalled = true;
				notifyAll();
			}
			return;
		}
		
		// center the window on the screen
		int imgWith = image.getWidth(this);
		int imgHeight = image.getHeight(this);
		setSize(imgWith, imgHeight);
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenDim.width - imgWith) / 2, (screenDim.height - imgHeight) / 2);
		
		MouseAdapter disposeOnClick = new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				synchronized(SplashWindow.this)
				{
					SplashWindow.this.paintCalled = true;
					SplashWindow.this.notifyAll();
				}
				dispose();
			}
		};
		
		addMouseListener(disposeOnClick);
	}
	
	@Override
	public void update(Graphics g)
	{
		paint(g);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, this);
		
		if (!paintCalled)
		{
			paintCalled = true;
			synchronized (this) {notifyAll();}
		}
	}
	
	public static void splash(Image image)
	{
		Frame f = new Frame();
		
		instance = new SplashWindow(f, image);
		instance.setVisible(true);
		
        if (!EventQueue.isDispatchThread()
				&& Runtime.getRuntime().availableProcessors() == 1)
		{
			synchronized (instance)
			{
				while (!instance.paintCalled)
				{
					try
					{
						instance.wait();
					} catch (InterruptedException e)
					{
					}
				}
			}
		}
	}
	
    public static void splash(URL imageURL)
	{
		if (imageURL != null)
		{
			splash(Toolkit.getDefaultToolkit().createImage(imageURL));
		}
	}
    
    public static void disposeSlash()
    {
    	if (instance != null)
    	{
    		instance.getOwner().dispose();
    		instance = null;
    	}
    }
    
    public static void invokeMain(String className, String[] args)
    {
    	try
    	{
    		Class.forName(className).getMethod("main",
					new Class[] { String[].class }).invoke(null,
					new Object[] { args });
    	}
    	catch (Exception e)
    	{
            InternalError error = new InternalError("Failed to invoke main method");
            error.initCause(e);
            throw error;
    	}
    }

}

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;


public class MFrame extends JFrame implements ComponentListener, ActionListener{
	
	private JPasswordField pass;
	private JTabbedPane tabbedPane;
	private int width = 750;
	private int height = 550;
	
	// COLORS
	private Color BACKGROUND = Color.DARK_GRAY;
	private Color BACKGROUND_COMPONENT = Color.LIGHT_GRAY;
	private Color FOREGROUND_COMPONENT = Color.DARK_GRAY;
	
	// TWEETER
	private MyTweetController mtc;
	
	private JSplitPane twitterPanel; // contenedor para el panel de botones y scrollPane
	private JPanel buttonsTwitter; // panel de los botones de tweeter
	private JScrollPane postTwitter; // panel del contenido de tweeter
	
	
	private JLabel userImage;
	private DButton homeTimeLine; // boton para mostrar el Home Time Line
	private DButton userTimeLine; // boton para mostrar la mis tweets
	private DButton userTweet;// boton para postear un tweet
	
	// FACEBOOK
	
	public MFrame() throws TwitterException{
		
		this.setIconImage( Toolkit.getDefaultToolkit().getImage("img/musolini.png"));
		this.setLayout( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.getContentPane().setBackground( BACKGROUND );
		this.setSize( 1000, 700 );
		// this.setResizable( false );
		this.setLocationRelativeTo( null );
		this.setMinimumSize( new Dimension( 600 , 350 ) );
		this.addComponentListener( this );
		
		// crear el JTabbedPane
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds( 200, 100 , width , height );
		
		//***************************************************************************************************
		//								TWEETER
		// crea los paneles de el tweeter
		twitterPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		buttonsTwitter = new JPanel( );
		buttonsTwitter.setBackground( BACKGROUND_COMPONENT );
		postTwitter = new JScrollPane( );
		postTwitter.setBackground( BACKGROUND_COMPONENT );
		
		// crea las propiedades del panel general
		twitterPanel.setDividerLocation( tabbedPane.getBounds().width / 5 );
		twitterPanel.setDividerSize(3);
		
		// crea las propiedades de los JScrollPane
		postTwitter.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
		postTwitter.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		
		// crea y agrega los botones al panel de botones de tweeter
		homeTimeLine = new DButton( "img/home.png" , BACKGROUND_COMPONENT , FOREGROUND_COMPONENT);
		userTimeLine = new DButton( "img/user.png" , BACKGROUND_COMPONENT , FOREGROUND_COMPONENT);
		userTweet = new DButton( "img/post.png" , BACKGROUND_COMPONENT , FOREGROUND_COMPONENT);
		
		buttonsTwitter.add(homeTimeLine);
		buttonsTwitter.add(userTimeLine);
		buttonsTwitter.add(userTweet);
		
		// agrega los tooltip a los botones
		homeTimeLine.setToolTipText( "Home Time Line" );
		userTimeLine.setToolTipText( "User Time Line" );
		userTweet.setToolTipText( "Postear un Tweet" );
		
		// agrega el panel de botones y el panel de scroll
		// a el panel general del tweeter
		twitterPanel.setLeftComponent( buttonsTwitter );
		twitterPanel.setRightComponent( postTwitter );
		
		// agrega el panel general al JTabbedPane del tweeter
		tabbedPane.addTab( null , new ImageIcon("img/twitter.png") , twitterPanel , "Twitter");
		
		// agrega los listener a los botones
		this.homeTimeLine.addActionListener( this );
		this.userTimeLine.addActionListener( this );
		this.userTweet.addActionListener( this );
		
		// this.userImage();
		this.homeTimeLine();
		// this.userTimeLine();
		
		//****************************************************************************************************
		// agregar el tabbedPane al frame
		this.add(tabbedPane);
		
		/*pass = new JPasswordField();
		pass.setBounds(200, 300, 200, 50);
		pass.setBackground( Color.DARK_GRAY );
		pass.setForeground( Color.WHITE );
		this.add(pass);*/
		
	}
	
	public void userImage() throws IllegalStateException, TwitterException{
		// agrega la imagen de usuario
		mtc = new MyTweetController();
		User user = mtc.getTwitterInstance().showUser( mtc.getTwitterInstance().getId() );
		ImageIcon img = new ImageIcon( user.getProfileImageURL() );
		userImage.setIcon( img );
		userImage.setBounds( 10 ,  10 , 50 , 50 );
	}
	
	
	public void homeTimeLine() throws TwitterException{

		mtc = new MyTweetController();
		List<Status> st = mtc.getHomeTimeLine();
		
		Box box = Box.createVerticalBox();
		
		// JLabel postTweeter;
		
		JTextArea postTwitter = new JTextArea( "HOME TIME LINE\n" );
		postTwitter.setLineWrap(true);
		postTwitter.setWrapStyleWord(true);
		postTwitter.setEditable(false);
		postTwitter.setBackground( BACKGROUND_COMPONENT );
		postTwitter.setForeground( FOREGROUND_COMPONENT );
		box.add( postTwitter );
		
		for( Status sts : st){
			
			postTwitter = new JTextArea( sts.getUser().getName() + ":\n"
								+ sts.getText() + "\n");
			
			postTwitter.setLineWrap(true);
			postTwitter.setWrapStyleWord(true);
			postTwitter.setEditable(false);
			postTwitter.setBackground( BACKGROUND_COMPONENT );
			postTwitter.setForeground( FOREGROUND_COMPONENT );
			
			box.add( postTwitter );
			
			/*postTweeter = new JLabel(sts.getUser().getName() + " : ");
			box.add( postTweeter );
			
			postTweeter = new JLabel("    " + sts.getText());
			box.add( postTweeter );
			
			postTweeter = new JLabel(" ");
			box.add( postTweeter );
			*/
		}
		
		this.postTwitter.getViewport().add( box );
		
	}

	public void userTimeLine() throws TwitterException{

		mtc = new MyTweetController();
		List<Status> st = mtc.getUserTimeLine();
		
		Box box = Box.createVerticalBox();
		JTextArea postTwitter = new JTextArea( "USER TIME LINE\n" );
		postTwitter.setLineWrap(true);
		postTwitter.setWrapStyleWord(true);
		postTwitter.setEditable(false);
		postTwitter.setBackground( BACKGROUND_COMPONENT );
		postTwitter.setForeground( FOREGROUND_COMPONENT );
		box.add( postTwitter );
		
		for( Status sts : st){
			
			postTwitter = new JTextArea( sts.getUser().getName() + ":\n"
								+ sts.getText() + "\n");
			
			postTwitter.setLineWrap(true);
			postTwitter.setWrapStyleWord(true);
			postTwitter.setEditable(false);
			postTwitter.setBackground( BACKGROUND_COMPONENT );
			postTwitter.setForeground( FOREGROUND_COMPONENT );
			
			box.add( postTwitter );
			
		}
		
		this.postTwitter.getViewport().add( box );
		
	}
	
	public void userTweet( ) throws TwitterException{
		String post = JOptionPane.showInputDialog( "Escribe tu post" );
		mtc.getTwitterInstance().updateStatus( post );
		try { Thread.sleep( 1000 ); } catch (InterruptedException e2) { e2.printStackTrace(); }
		
	}
	
	@Override
	public void componentHidden(ComponentEvent arg0) { }
	@Override
	public void componentMoved(ComponentEvent arg0) { }
	@Override
	public void componentShown(ComponentEvent arg0) { }
	@Override
	public void componentResized(ComponentEvent arg0) { 
		tabbedPane.setBounds( 200 , 100 , (int) (this.getBounds().getWidth() - 250) , (int) (this.getBounds().getHeight() - 150) );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == this.homeTimeLine ){
			try { this.homeTimeLine(); } catch (TwitterException e1) { e1.printStackTrace(); }
		}
		
		if( e.getSource() == this.userTimeLine ){
			try { this.userTimeLine(); } catch (TwitterException e1) { e1.printStackTrace(); }
		}
		
		if( e.getSource() == this.userTweet ){
			try { this.userTweet(); } catch (TwitterException e1) { e1.printStackTrace(); }
		}
		
	}
	
}

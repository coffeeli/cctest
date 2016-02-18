package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

public class TEXT extends JFrame{
	UndoManager manager = new UndoManager();  
    JTextArea text = new JTextArea();  
    JFileChooser jfc = new JFileChooser();  
    String jsb = "新建记事本";  
    File file;  
    JMenuBar menu;  
    JMenu File_bar, Edit_bar, Format_bar, View_bar, Help_bar;  
    JMenuItem File_bar_creat, File_bar_open, File_bar_save, File_bar_othersave, File_bar_exit;  
    JMenuItem Edit_bar_Revoke, Edit_bar_shear, Edit_bar_copy, Edit_bar_paste, Edit_bar_delete;  
    JMenuItem Format_bar_hl, m30, Format_bar_ztxz, Format_bar_ztsz, View_bar_about, Help_bar_help;  
  
    /* 文件格式过滤器 */  
    public class filter extends javax.swing.filechooser.FileFilter  
    {  
        public boolean accept(File file)  
        {  
            String name = file.getName();  
            name.toString(); // 该字符串中的数字被转换为字母  
            name.toLowerCase();// 该字符串中的字母被转换为小写字母  
            /* 文件后缀是.txt且是个目录 */  
            if (name.endsWith(".txt") || file.isDirectory())  
            {  
                return true;  
            } else  
                return false;  
        }  
  
        /* 将引用具体子类的子类对象的方法,不可以省略类中的getDescription(),原因是编译器只允许调用在类中声明的方法. */  
        public String getDescription()  
        {  
            return ".txt";  
        }  
    }  
  
    /* 将菜单项 JMenu添加菜单 JMenuBar */  
    public JMenu AddBar(String name, JMenuBar menu)  
    {  
        JMenu jmenu = new JMenu(name);  
        menu.add(jmenu);  
        return jmenu;  
    }  
  
    /* 将菜单项JMenuItem添加到菜单JMenu */  
    public JMenuItem AddItem(String name, JMenu menu)  
    {  
        JMenuItem jmenu = new JMenuItem(name);  
        menu.add(jmenu);  
        return jmenu;  
    }  
  
    TEXT aaa1;  
  
    {  
        setTitle(jsb); // 设置窗口标题  
        setBounds(250, 250, 500, 500);// 设置边界  
        JMenuBar menu = new JMenuBar(); // 添加菜单 JMenuBar  
        this.setJMenuBar(menu);// 调用this方法  
        text.getDocument().addUndoableEditListener(manager);// 用于获得程序当前有效的文档  
        /* 
         * Font是JAVA中的字体类，PLAIN是Font类中的静态常量( static final ) ,表示是:普通样式常量 BOLD 
         * :粗体样式常量 ,ITALIC: 斜体样式常量,14:磅 
         */  
        text.setFont(new Font("宋体", Font.PLAIN, 14));  
  
        /* 光标颜色 */  
        text.setCaretColor(Color.gray);  
  
        /* 选中字体颜色 */  
        text.setSelectedTextColor(Color.blue);  
  
        /* 选中背景颜色 */  
        text.setSelectionColor(Color.green);  
  
        /* 是否换行 */  
        text.setLineWrap(true);  
  
        /* 是否单词边界换行（即有空白） */  
        text.setWrapStyleWord(true);  
  
        /* 文本区与边框的间距，四个参数分别为上、左、下、右 */  
        text.setMargin(new Insets(3, 5, 3, 5));  
  
        /* 开启或关闭自动拖动处理 */  
        text.setDragEnabled(true);  
  
        /* 创建一个 JScrollPane，它将视图组件显示在一个视口中，视图位置可使用一对滚动条控制 */  
        add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,  
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));  
  
        File_bar = this.AddBar("文件(F)", menu);  
        Edit_bar = this.AddBar("编辑(E)", menu);  
        Format_bar = this.AddBar("格式(O)", menu);  
        View_bar = this.AddBar("查看(V)", menu);  
        Help_bar = this.AddBar("帮助(H)", menu);  
  
        /* 文件选项 */  
        /* 新建选项 */  
        File_bar_creat = this.AddItem("新建(N)   Ctrl+N", File_bar);  
        File_bar_creat.addActionListener(new ActionListener()  
        {  
            // @Override  
            public void actionPerformed(ActionEvent arg0)  
            {  
                // TODO 自动生成的方法存根  
                text.setText("");  
            }  
        });  
  
        /* 打开选项 */  
        File_bar_open = this.AddItem("打开(O)   Ctrl+O", File_bar);  
        File_bar_open.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                try  
                {  
                    jfc.setCurrentDirectory(new File("."));// 设置当前目录  
                    // jfc.setFileFilter(new filter()); // 过滤文件  
                    jfc.setFileFilter(new filter()); // 过滤文件  
                    jfc.setAcceptAllFileFilterUsed(false); // 全选文件  
                    // jfc.addChoosableFileFilter(new  
                    // javax.swing.filechooser.FileFilter()); //不能实例化类型  
                    jfc.showOpenDialog(null); // 弹出一个 "Open File" 文件选择器对话框。  
                    file = jfc.getSelectedFile(); // 获取已经选择目录  
                    jsb = file.getName(); // 获取目录名  
                    setTitle(jsb); // 显示目录名  
                    int length = (int) (jfc.getSelectedFile()).length();  
                    char[] ch = new char[length];  
                    FileReader fr = new FileReader(file);  
                    fr.read(ch);  
                    jsb = new String(ch);  
                    text.setText(jsb.trim()); // 获得对象的字段的值，然后转成string类型，并且去掉前后空白~~ToString()是转化为字符串的方法  
                    // Trim()是去两边空格  
                } catch (Exception e)  
                {  
                    JOptionPane.showMessageDialog(null, e);  
                }  
            }  
        });  
  
        /* 保存选项 = (1)如果文件为空，新建一个目录保存；(2)如果当前文件存在，直接保存 */  
        File_bar_save = this.AddItem("保存(S)   Ctrl+O", File_bar);  
        File_bar_save.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                if (file == null)  
                {  
                    try  
                    {  
                        jfc = new JFileChooser();  
                        jfc.setCurrentDirectory(null);  
                        jsb = JOptionPane.showInputDialog("请输入文件名：") + ".txt";  
                        /* 
                         * setSelectedFile返回的是对话框中选中的文件但如果对话框类型是showSaveDialog的话, 
                         * 那么这里返回的值是你要保存的文件, 这个文件可能存在,可能不存在,就是你在对话框中输入的文件名了, 
                         * 既然知道了文件,如果不存在,就新建一个,然后向文件写入数据,这样就可以实现保存了 
                         */  
                        jfc.setSelectedFile(new File(jsb));  
                        jfc.setFileFilter(new filter());  
                        int temp = jfc.showSaveDialog(null);  
                        if (temp == jfc.APPROVE_OPTION)  
                        {  
                            if (file != null)  
                                file.delete();  
                            file = new File(jfc.getCurrentDirectory(), jsb);  
                            file.createNewFile();  
                            FileWriter fw = new FileWriter(file);  
                            fw.write(text.getText());  
                            fw.close();  
                        }  
                    } catch (Exception e)  
                    {  
                        JOptionPane.showMessageDialog(null, e);  
                    }  
                } else  
                {  
                    try  
                    {  
                        FileWriter fw = new FileWriter(file);  
                        fw.write(text.getText());  
                        fw.close();  
                    } catch (Exception e)  
                    {  
                        JOptionPane.showMessageDialog(null, e);  
                    }  
                }  
            }  
        });  
  
        /* 另存为选项 */  
        File_bar_othersave = this.AddItem("另存为(A)...", File_bar);  
        File_bar_othersave.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                // file fw = new file();  
                jfc = new JFileChooser();  
                jfc.setCurrentDirectory(new File("."));  
                try  
                {  
                    if (file == null)  
                    {  
                        jsb = JOptionPane.showInputDialog("请输入文件名：") + ".txt";  
                    } else  
                        jsb = file.getName();  
                    jfc.setSelectedFile(new File(jsb));  
                    jfc.setFileFilter(new filter());  
                    int temp = jfc.showSaveDialog(null);  
                    if (temp == jfc.APPROVE_OPTION)  
                    {  
                        if (file != null)  
                            file.delete();  
                        file = new File(jfc.getCurrentDirectory(), jsb);  
                        file.createNewFile();  
                        FileWriter fw = new FileWriter(file);  
                        fw.write(text.getText());  
                        fw.close();  
                    }  
                } catch (Exception e)  
                {  
                    JOptionPane.showMessageDialog(null, e);  
                }  
            }  
        });  
  
        /* 将默认大小的分隔符添加到工具栏的末尾。 */  
        File_bar.addSeparator();  
  
        /* 退出选项 + 退出提示 */  
        File_bar_exit = this.AddItem("退出(X)", File_bar);  
        File_bar_exit.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                int state = JOptionPane.showConfirmDialog(aaa1, "您确定要退出？退出前请确定您的文件已保存");  
                if (state == JOptionPane.OK_OPTION)  
                    System.exit(0);  
            }  
        });  
        /* 编辑选项 */  
        /* 撤消选项 */  
        Edit_bar_Revoke = this.AddItem("撤销(U)   Ctrl+Z", Edit_bar);  
        Edit_bar_Revoke.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                if (manager.canUndo())  
                    manager.undo();  
            }  
        });  
  
        /* 剪切选项 */  
        Edit_bar_shear = this.AddItem("剪切(T)   Ctrl+X", Edit_bar);  
        Edit_bar_shear.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                text.cut();  
            }  
        });  
  
        /* 复制选项 */  
        Edit_bar_copy = this.AddItem("复制(C)   Ctrl+C", Edit_bar);  
        Edit_bar_copy.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                text.copy();  
            }  
        });  
  
        /* 粘贴选项 */  
        Edit_bar_paste = this.AddItem("粘贴(P)   Ctrl+V", Edit_bar);  
        Edit_bar_paste.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                text.paste();  
            }  
        });  
  
        /* 删除选项=用空格替换从当前选取的开始到结束 */  
        Edit_bar_delete = this.AddItem("删除(L)   Del", Edit_bar);  
        Edit_bar_delete.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                text.replaceRange("", text.getSelectionStart(), text.getSelectionEnd());  
            }  
        });  
  
        /* 自动换行选项 */  
        // m26 = this.AddItem("自动换行(W)", m3);  
        final JCheckBoxMenuItem Format_bar_hl = new JCheckBoxMenuItem("自动换行", true);  
        Format_bar.add(Format_bar_hl);  
        Format_bar_hl.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                /* 根据文件名获取文件信息 */  
                if (Format_bar_hl.getState())  
                    text.setLineWrap(true);  
                else  
                    text.setLineWrap(false);  
            }  
        });  
  
        /* 字体选项 */  
        /* 
         * 字体格式设置选项 GraphicsEnvironment 类描述了 Java(tm) 应用程序在特定平台上可用 
         *  
         * 的 GraphicsDevice 对象和 Font 对象的集合 
         */  
        Format_bar_ztxz = this.AddItem("字体选择(F)", Format_bar);  
        Format_bar_ztxz.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                /* 获取本地图形环境 */  
                GraphicsEnvironment gr = GraphicsEnvironment.getLocalGraphicsEnvironment();  
                /* 字体名称列表框 */  
                JList fontnames = new JList(gr.getAvailableFontFamilyNames());  
                /* JScrollPane 管理视口、可选的垂直和水平滚动条以及可选的行和列标题视口 */  
                int selection = JOptionPane.showConfirmDialog(null, new JScrollPane(fontnames), "请选择字体",  
                        JOptionPane.OK_CANCEL_OPTION);  
                // Object selectedFont = fontnames.get  
                Object selectedFont = fontnames.getSelectedValue();  
                // Object selectedFont = fontnames.getSelectedIndex();  
                if (selection == JOptionPane.OK_OPTION && selectedFont != null)  
                {  
                    text.setFont(new Font(fontnames.getSelectedValue().toString(), Font.PLAIN, 20));  
                }  
            }  
        });  
        /* 字体颜色设置选项 */  
        Format_bar_ztsz = this.AddItem("颜色(C)", Format_bar);  
        Format_bar_ztsz.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                Color color = JColorChooser.showDialog(null, "文字颜色选择", Color.BLACK);  
                text.setForeground(color);  
            }  
        });  
  
        View_bar_about = this.AddItem("关于记事本(About)", View_bar);  
        View_bar_about.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                JOptionPane.showMessageDialog(null, "记事本\n开发语言：JAVA\n开发者：【贺荣伟&&李苗】\n联系方式：rongweih1995@gmail.com", "关于",  
                        JOptionPane.PLAIN_MESSAGE);  
            }  
        });  
  
        Help_bar_help = this.AddItem("帮助选项(H)", Help_bar);  
        Help_bar_help.addActionListener(new ActionListener()  
        {  
            public void actionPerformed(ActionEvent event)  
            {  
                JOptionPane.showMessageDialog(null, "详细代码请移步\n博客：www.acmerbar.com", "帮助", JOptionPane.PLAIN_MESSAGE);  
            }  
        });  
        setResizable(true); // 窗体是否可变  
        setVisible(true); // 窗体是否可见  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
  
    public static void main(String args[])  
    {  
        TEXT example = new TEXT();  
    } 
}

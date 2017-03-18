package com.palewoods.mytaobao.activity;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.palewoods.mytaobao.R;
import com.palewoods.mytaobao.dao.biz.ProductManager;
import com.palewoods.mytaobao.dao.biz.UserManager;
import com.palewoods.mytaobao.model.Product;
import com.palewoods.mytaobao.model.User;

import java.util.List;

public class MainActivity extends ListActivity {

    private Context context = null;
    private LayoutInflater inflater = null;
    private User loginUser = null;
    private UserManager userManager = null;
    private ProductManager productManager = null;//管理Product的对象

    //定义一些创建对话框对应的标识
    private static final int DIALOG_LOGIN = 1;//登录对话框
    private static final int DIALOG_EXIT = -1;//退出对话框，代表按下返回键弹出的对话框
    private static final int DIALOG_REG = 2;//代表注册界面

    //定义关于ListView的相关常量
    private static final int PAGESIZE = 5;//每次取的条数
    private List<Product> products = null;//用来保存商品的list
    private ListView lv_Product = null;//ListView
    private MyAdapter adapter = null;//自定义Adapter的对象

    //变量
    private int PageIndex = 0;//用于保存当前是第几页，0代表第一页

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();//系统初始化
        showDialog(DIALOG_LOGIN);//调出登录对话框
//        setContentView(R.layout.activity_main);
    }

    /*初始化系统*/
    private void init() {
        context = this;
        this.inflater = LayoutInflater.from(this);
        userManager = new UserManager();
    }

    /*初始化MainActivity的UI*/
    private void initMainActivityUI() {
        //准备product数据
        productManager = new ProductManager();
        products = productManager.getProductByPager(PageIndex,PAGESIZE);
//        products = productManager.getAll();

        /*继承自ListActivity，不需要setContentView，如果非要使用setContentView，
        则在对应的布局中添加一个ListView，并且ListView的id为android.R.id.list*/
//        setContentView(R.layout.activity_main);
        adapter = new MyAdapter(context);
        setListAdapter(adapter);

        lv_Product = getListView();//通过getListView()获取ListActivity内部的ListView对象
        lv_Product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //通过id判断点击的是那一项
//                if (id == -1) {
//                    Toast.makeText(context, "表头", Toast.LENGTH_SHORT).show();
//                } else if (id == 1) {
//                    Toast.makeText(context, "更多", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
                if (id == -1) {
                }else if (id == 1) {
//                    Toast.makeText(context, "更多", Toast.LENGTH_SHORT).show();
                    more();//获取更多数据，一次添加1页的数据
                } else {
                    Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /*更多*/
    private void more(){
            /*获取更多的数据*/
        PageIndex++;//页面索引++
        //获取数据，一页的数据
        List<Product> moreProduct = productManager.getProductByPager(PageIndex,PAGESIZE);

        if (moreProduct != null) {
            products.addAll(moreProduct);//将获取到的数据加入集合中
            MyAdapter adapter = new MyAdapter(context);
            setListAdapter(adapter);
                /*出错了*/
//                adapter.notifyDataSetChanged();//刷新ListView
                /*出错*/
        } else {
            Tool.ShowManager(context,"已经是最后一条了！");
        }
    }

    /*重写创建的对话框函数*/
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = this.buildDialog(id);
        return dialog;
    }

    /*根据ID创建不同的对话框*/
    private Dialog buildDialog(int flag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        switch (flag) {
            case DIALOG_LOGIN://登录界面
                createLoginDialog(builder);//创建登录界面
                break;
            case DIALOG_EXIT://退出界面
                createExitDialog(builder);//创建退出界面
                break;
            case DIALOG_REG://注册界面
                createRegDialog(builder);//创建注册界面
                break;
            default:
                break;
        }
        return builder.create();//把前面创建的界面创建并返回
    }

    /*创建退出对话框*/
    private void createExitDialog(AlertDialog.Builder builder) {
        builder.setTitle("警告")
                .setIcon(R.drawable.ic_login_dialog)
                .setMessage("您确定要退出吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("取消", null);
    }

    /*产生一个四位的随机数，转化成String类型*/
    private String getCheckCode() {
        String strInt = "";
        Integer i = new Integer((int) (Math.random() * 10000));
        strInt = String.valueOf(i);
        if (strInt.length() != 4) {
            return getCheckCode();
        } else {
            return strInt;
        }
    }

    /*创建注册对话框*/
    private void createRegDialog(AlertDialog.Builder builder) {
        View view_Reg = inflater.inflate(R.layout.reg_ui, null);
        builder.setTitle("注册").setIcon(R.drawable.ic_login_dialog).setView(view_Reg);

        final EditText etUserId_reg = (EditText) view_Reg.findViewById(R.id.et_reg_userId);
        //注册对话框的帐号EditText,必须前面加view
        final EditText etPassword_reg = (EditText) view_Reg.findViewById(R.id.et_reg_password);
        //注册对话框的密码EditText，必须前面加view
        final EditText etCheckCode_reg = (EditText) view_Reg.findViewById(R.id.et_reg_checkCode);
        //验证码EditText
        final TextView tvCheckCode_reg = (TextView) view_Reg.findViewById(R.id.tv_reg_checkCode);
        //验证码TextView
        tvCheckCode_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cheCode = getCheckCode();
                tvCheckCode_reg.setText(cheCode);
            }
        });

        Button btnReg = (Button) view_Reg.findViewById(R.id.btn_reg_id);//注册按钮
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(etUserId_reg.getText().toString(),etPassword_reg.getText().toString());
//                User result = userManager.Register(user);
                if (etCheckCode_reg.getText().toString().equals(tvCheckCode_reg.getText().toString())) {
                    User result = userManager.Register(user);
                    if (result != null) {
                        dismissDialog(DIALOG_REG);
                    } else {
                        Tool.ShowManager(context, "注册失败！！");
                        String cheCode = getCheckCode();
                        tvCheckCode_reg.setText(cheCode);
                    }
                } else {
                    Tool.ShowManager(context,"验证码错误！！");
                    String cheCode = getCheckCode();
                    tvCheckCode_reg.setText(cheCode);
                }
            }
        });

        Button btnExitReg = (Button) view_Reg.findViewById(R.id.btn_exit_reg);//退出按钮
        btnExitReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                dismissDialog(DIALOG_REG);
            }
        });
    }

    /*创建登录对话框*/
    private void createLoginDialog(AlertDialog.Builder builder) {
        View view_reg = inflater.inflate(R.layout.login_ui, null);
        builder.setTitle("登录").setIcon(R.drawable.ic_login_dialog).setView(view_reg);

        final EditText etUserId_login = (EditText) view_reg.findViewById(R.id.et_login_userId);
        //登录对话框的帐号EditText,必须前面加view
        final EditText etPassword_login = (EditText) view_reg.findViewById(R.id.et_login_password);
        //登录对话框的密码EditText，必须前面加view

        Button btnLogin = (Button) view_reg.findViewById(R.id.btn_login_id);//登录按钮
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser = userManager.Login(etUserId_login.getText().toString(),etPassword_login.getText().toString());
                if (loginUser != null) {
                    dismissDialog(DIALOG_LOGIN);
                    initMainActivityUI();//初始化MainActivity的UI
                } else {
                    Tool.ShowManager(context,"帐号或者密码错误，登录失败！！");
                }
            }
        });

        Button btnExit = (Button) view_reg.findViewById(R.id.btn_exit_id);//退出按钮
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

                /*注册EditText，必须前面加view 不然会在activity_main.xml中找view*/
        TextView reg_tv = (TextView) view_reg.findViewById(R.id.tv_reg);//注册TextView
        reg_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dismissDialog(DIALOG_LOGIN);
                showDialog(DIALOG_REG);
            }
        });
    }

    /*按下返回键*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            /*如果在主界面点击了返回键弹出退出对话框*/
            showDialog(DIALOG_EXIT);
            return true;
        }
        return false;
    }

    /*每次创建对话框都重复调用*/
    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        if (id == DIALOG_REG) {
            String checkCode = getCheckCode();//调用产生随机数的函数
            TextView textView = (TextView) dialog.findViewById(R.id.tv_reg_checkCode);
            textView.setText(checkCode);
        }
    }

    /*自定义Adapter*/
    class MyAdapter extends BaseAdapter {

        private Context context;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return products.size() + 2;//这里+2是因为要添加表头，和底部的更多视图，所以多了两项
        }

        @Override
        public Object getItem(int position) {
            return products.get(position - 1);//因为第一项是表头，所以-1才是正确的获取数据的起始位置
        }

        @Override
        public long getItemId(int position) {
            if (position == 0) {
                return -1;//如果是第一项，则返回-1，表示表头
            } else if (position == this.getCount() - 1) {
                return 1;//如果是最后一项则返回1，代表底部的更多那一项
            }else {
                return products.get(position - 1).getId();//同上
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (position == 0) {
                //判断是否为第一项，如果是则返回表头
                View v = inflater.inflate(R.layout.add_product_item, null);
                ImageView imageView = (ImageView) v.findViewById(R.id.add_image);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Tool.ShowManager(context,"添加");
                    }
                });
                return v;
            } else if (position == this.getCount() - 1) {
                //判断是否为最后一项，如果是加入表的底部
                View v = inflater.inflate(R.layout.more_items_view,null);
                return v;
            }else {
                //中间，显示商品数据
                if (convertView == null) {
                    //第一次调用，convertView值为空，通过LayoutInflater把布局文件转化成View控件
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.product_item, null);
                    holder = new Holder();
                    //把xml布局中的TextView的所有属性都设置给了new出来的Holder中的TextView的xianshi属性
                    holder.imageView = (ImageView) convertView.findViewById(R.id.prduct_item_Pic);
                    holder.productName = (TextView) convertView.findViewById(R.id.prduct_item_Name);
                    holder.productPrice = (TextView) convertView.findViewById(R.id.prduct_item_unitPrice);
                    //打标签
                    convertView.setTag(holder);
                } else {
                    //复用
                    holder = (Holder) convertView.getTag();
                }
                //因为第一项是表头，所以需要-1，再获取数据
                holder.imageView.setImageResource(products.get(position - 1).getPhoto());
                holder.productName.setText(position+"."+products.get(position - 1).getName());
                holder.productPrice.setText(String.valueOf(products.get(position - 1).getUnitPrice()));

                return convertView;
            }
        }
    }

    /*Adapter中用到的Holder类*/
    class Holder {
        ImageView imageView;
        TextView productName;
        TextView productPrice;
    }

    /*创建菜单项*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(1,1,1,"批量管理").setIcon(R.drawable.ic_new_product);
        menu.add(1,2,2,"搜索").setIcon(R.drawable.ic_login_dialog);
        menu.add(1,3,3,"按价格排序").setIcon(R.drawable.ic_add);
        return true;
    }

    /*菜单项目点击监听*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Tool.ShowManager(context,"批量管理");
                //实现具体功能
                break;
            case 2:
                Tool.ShowManager(context,"搜索");
                //实现具体功能
                break;
            case 3:
                Tool.ShowManager(context,"按价格排序");
                //实现具体功能
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

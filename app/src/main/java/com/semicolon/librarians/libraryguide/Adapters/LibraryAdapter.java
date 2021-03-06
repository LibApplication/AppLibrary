package com.semicolon.librarians.libraryguide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.semicolon.librarians.libraryguide.Activities.Activity_Profile;
import com.semicolon.librarians.libraryguide.Activities.Chat_Activity;
import com.semicolon.librarians.libraryguide.Activities.HomeActivity;
import com.semicolon.librarians.libraryguide.Models.LibraryModel;
import com.semicolon.librarians.libraryguide.R;
import com.semicolon.librarians.libraryguide.Services.Tags;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import java.util.List;

/**
 * Created by Delta on 22/01/2018.
 */

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder>  {

    List<LibraryModel> libraryModelList;
    HomeActivity homeActivity;
    Context context;

    public LibraryAdapter(List<LibraryModel> libraryModelList, Context context) {
        this.libraryModelList = libraryModelList;
        this.context = context;
        homeActivity = (HomeActivity) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.library_recyclerview_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final LibraryModel libraryModel = libraryModelList.get(position);
        holder.BindData(libraryModel);
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.rec_anim);
        holder.itemView.startAnimation(animation);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LibraryModel libraryModel1 = libraryModelList.get(holder.getAdapterPosition());
                View custom_view = LayoutInflater.from(context).inflate(R.layout.custom_alert_msg_profile,null);
                TextView open_profile = (TextView) custom_view.findViewById(R.id.open_profile_tv);
                TextView send_msg = (TextView) custom_view.findViewById(R.id.send_msg_tv);
                Button cancelBtn = (Button) custom_view.findViewById(R.id.cancelBtn);

                final LovelyCustomDialog dialog = new LovelyCustomDialog(context);
                dialog.setCancelable(true);
                dialog.setTopTitle(context.getString(R.string.sel_opt));
                dialog.setTopColor(ActivityCompat.getColor(context,R.color.centercolor));
                dialog.setTopTitleColor(ActivityCompat.getColor(context,R.color.base));

                open_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(homeActivity,Activity_Profile.class);
                        intent.putExtra("who_visit_myProfile","visitor");
                        intent.putExtra("libraryData",libraryModel1);
                        context.startActivity(intent);

                        dialog.dismiss();

                    }
                });
                send_msg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (homeActivity.user_Data!=null)
                        {
                            Log.e("adapter library",homeActivity.user_Data.getUserId()+"  "+libraryModel1.getLib_username());
                            homeActivity.chatRoomPresenter.Create_ChatRoom(homeActivity.user_Data.getUserId(),libraryModel.getLib_username());
                            Intent intent = new Intent(context, Chat_Activity.class);
                            intent.putExtra("curr_userType","user");
                            intent.putExtra("chat_userType","library");
                            intent.putExtra("curr_user",homeActivity.user_Data);
                            intent.putExtra("chat_user",libraryModel1);
                            context.startActivity(intent);
                            dialog.dismiss();
                        }else if (homeActivity.publisher_Model!=null)
                        {
                            homeActivity.chatRoomPresenter.Create_ChatRoom(homeActivity.publisher_Model.getPub_username(),libraryModel1.getLib_username());

                            Log.e("adapter library",homeActivity.publisher_Model.getPub_username()+"  "+libraryModel1.getLib_username());

                            Intent intent = new Intent(context, Chat_Activity.class);
                            intent.putExtra("curr_userType","publisher");
                            intent.putExtra("chat_userType","library");
                            intent.putExtra("curr_user",homeActivity.publisher_Model);
                            intent.putExtra("chat_user",libraryModel1);
                            context.startActivity(intent);
                            dialog.dismiss();

                        }
                        else if (homeActivity.library_Model!=null)
                        {
                            Log.e("adapter library",homeActivity.library_Model.getLib_username()+"  "+libraryModel1.getLib_username());

                            homeActivity.chatRoomPresenter.Create_ChatRoom(homeActivity.library_Model.getLib_username(),libraryModel1.getLib_username());

                            Intent intent = new Intent(context, Chat_Activity.class);
                            intent.putExtra("curr_userType","library");
                            intent.putExtra("chat_userType","library");
                            intent.putExtra("curr_user",homeActivity.library_Model);
                            intent.putExtra("chat_user",libraryModel1);
                            context.startActivity(intent);
                            dialog.dismiss();

                        }
                        else if (homeActivity.university_Model!=null)
                        {
                            Log.e("adapter library",homeActivity.university_Model.getUni_username()+"  "+libraryModel1.getLib_username());

                            homeActivity.chatRoomPresenter.Create_ChatRoom(homeActivity.university_Model.getUni_username(),libraryModel.getLib_username());

                            Intent intent = new Intent(context, Chat_Activity.class);
                            intent.putExtra("curr_userType","university");
                            intent.putExtra("chat_userType","library");
                            intent.putExtra("curr_user",homeActivity.university_Model);
                            intent.putExtra("chat_user",libraryModel1);
                            context.startActivity(intent);
                            dialog.dismiss();

                        }
                        else if (homeActivity.company_Model!=null)
                        {
                            Log.e("adapter library",homeActivity.company_Model.getComp_name()+"  "+libraryModel1.getLib_username());

                            homeActivity.chatRoomPresenter.Create_ChatRoom(homeActivity.company_Model.getComp_username(),libraryModel.getLib_username());

                            Intent intent = new Intent(context, Chat_Activity.class);
                            intent.putExtra("curr_userType","company");
                            intent.putExtra("chat_userType","library");
                            intent.putExtra("curr_user",homeActivity.company_Model);
                            intent.putExtra("chat_user",libraryModel1);
                            context.startActivity(intent);
                            dialog.dismiss();

                        }
                    }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setView(custom_view);
                dialog.create();
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return libraryModelList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {
        Target target;
        ImageView library_image;
        TextView library_name,library_country,library_type,library_services,more;

        public ViewHolder(View itemView) {
            super(itemView);

            initTitleView(itemView);


        }



        private void initTitleView(View titleView) {

            library_image       = (ImageView) titleView.findViewById(R.id.library_image);
            library_name        = (TextView) titleView.findViewById(R.id.library_name);
            library_country         = (TextView) titleView.findViewById(R.id.library_country);
            library_type      = (TextView) titleView.findViewById(R.id.library_type);
            more = (TextView) itemView.findViewById(R.id.more);


        }
        public void BindData(final LibraryModel libraryModel)
        {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),Tags.font);

            target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    library_image.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };
            if (!libraryModel.getUser_photo().equals("0"))
            {
                Picasso.with(context).load(Tags.image_path+libraryModel.getUser_photo()).placeholder(R.drawable.user_profile).into(target);

            }else
                {
                    Picasso.with(context).load(R.drawable.user_profile).into(target);

                }

           /* Long sd = Long.getLong(PublisherModel.getPublishertartDate());
            Long ed = Long.getLong(PublisherModel.getJobEndDate());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd,MM,yy");
            String s_Date = dateFormat.format(new Date(sd));
            String e_Date = dateFormat.format(new Date(ed));*/



            library_name.setTypeface(typeface);
            library_country.setTypeface(typeface);
            library_type.setTypeface(typeface);
            more.setTypeface(typeface);

            library_name.setText(libraryModel.getLib_name());
            if (libraryModel.getTitle()==null)
            {
                library_country.setText(libraryModel.getLib_country());

            }else
                {
                    library_country.setText(libraryModel.getTitle());

                }
            //library_services.setText(publisherModel.getPub_email());
            library_type.setText(libraryModel.getType_title());
            Log.e("libusername",""+libraryModel.getLib_username());




        }
    }


    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}

